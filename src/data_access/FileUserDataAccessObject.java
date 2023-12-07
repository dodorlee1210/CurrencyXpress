package data_access;

import entity.Account;
import entity.User;
import entity.UserFactory;
import entity.banks.*;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import entity.banks.Bank;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();

    private UserFactory userFactory;

    public FileUserDataAccessObject(String csvPath, UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("Bank Type", 2);
        headers.put("Initial Balance(EUR)", 3);
        headers.put("Account Holder", 4);
        headers.put("Foreign Currencies...", 5);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("username,password,Bank Type,Initial Balance(EUR),Account Holder,Foreign Currencies...");

                String row;

                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String bankType = String.valueOf(col[headers.get("Bank Type")]);
                    String accountHolder = String.valueOf(col[headers.get("Account Holder")]);
                    double initialBalance;

                    try {
                        initialBalance = Double.parseDouble(col[headers.get("Initial Balance(EUR)")]);
                    }

                    catch (Exception e) {
                        System.out.println(e.getStackTrace()); // change exception e to specific exception later
                        initialBalance = 0.0;
                    }

                    User user = userFactory.create(username, password, getBank(bankType), initialBalance, accountHolder);

                    if (col.length > 5) {   // user has initial foreign currency balance
                        Account userAccount = user.getUserAccount();

                        for (int i = 5; i < col.length; i = i + 2) {
                            userAccount.setForeignCurrency(col[i], Double.parseDouble(col[i + 1]));
                        }
                    }

                    accounts.put(username, user);
                }
            }
        }
    }

    /**
     * Return the bank object that is specified by the parameter: bankType
     * @param bankType The object type to return
     * @return Specified bank object
     */
    private Bank getBank(String bankType) {
        return switch (bankType) {
            case "BMO" -> new BMO();
            case "CIBC" -> new CIBC();
            case "RBC" -> new RBC();
            case "Scotia" -> new Scotia();
            default -> new TD();
        };
    }

    /**
     * Save the given user to the CSV file.
     * If the user is new, then add the given user object to the user collection before
     * saving it to the CSV file.
     * @param user the user that has been added or modified
     */
    @Override
    public void save(User user) {
        String username = user.getUsername();

        if (!accounts.containsKey(username)) {   // add new user
            accounts.put(username, user);
        }

        this.save();
    }

    /**
     * Get the user object from the given username
     * @param username the username
     * @return the user object corresponding to the username
     */
    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    /**
     * The method that modifies (update) the CSV file
     */
    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                // Bank Type,Initial Balance,Account Holder
                Account userAccount = user.getUserAccount();

                // if the user has a foreign currency and have to perform String '+' operation repeatedly,
                // the StringBuilder performance has an absolute advantage rather than just using
                // '+' operation in the loop
                StringBuilder sb = new StringBuilder(String.format("%s,%s,%s,%s,%s",
                        user.getUsername(), user.getPassword(), userAccount.getBankName(),
                        userAccount.getBalance(), userAccount.getAccountHolder()));

                if (userAccount.hasForeignCurrency()) {    // user has at least 1 foreign currency
                    String[][] allForeignCurrency = userAccount.getAllForeignCurrencies();

                    for (String[] foreignCurrency : allForeignCurrency) {
                        sb.append(String.format(",%s,%s", foreignCurrency[0], foreignCurrency[1]));
                    }
                }

                writer.write(sb.toString());
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Clear all the users that have been signed up to this service
     * @return the cleared username
     */
    public ArrayList<String> clear() {
        ArrayList<String> usernames = new ArrayList<>();

        for (User username : accounts.values()) {
            usernames.add(username.getUsername());
        }

        accounts.clear();
        save();

        return  usernames;
    }

    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

}

