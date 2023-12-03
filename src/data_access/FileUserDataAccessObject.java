package data_access;

import entity.Account;
import entity.User;
import entity.UserFactory;
import entity.banks.*;
import entity.UserManagement;
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
        headers.put("Bank Name", 2);
        headers.put("Initial Balance", 3);
        headers.put("Account Holder", 4);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("username,password,Bank Name,Initial Balance,Account Holder");

                String row;

                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String bankName = String.valueOf(col[headers.get("Bank Name")]);
                    String accountHolder = String.valueOf(col[headers.get("Account Holder")]);
                    double initialBalance;

                    try {
                        initialBalance = Double.parseDouble(col[headers.get("Initial Balance")]);
                    }

                    catch (Exception e) {
                        System.out.println(e.getStackTrace()); // change exception e to specific exception later
                        initialBalance = 0.0;
                    }

                    User user = userFactory.create(username, password, bankName, initialBalance, accountHolder);
                    accounts.put(username, user);
                }
            }
        }
    }

    @Override
    public void save(User user) {
        accounts.put(user.getUsername(), user);
        this.save();
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                // Bank Type,Initial Balance,Account Holder
                Account userAccount = user.getUserAccount();
                String line = String.format("%s,%s,%s,%s,%s",
                        user.getUsername(), user.getPassword(), userAccount.getBankName(),
                        userAccount.getBalance(), userAccount.getAccountHolder());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Implemented
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
