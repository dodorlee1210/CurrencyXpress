package entity;

import entity.banks.Bank;

public class CommonUser implements User {

    private final String username;
    private final String password;
    private Account userAccount;

    public CommonUser(String username, String password, Bank bank, double initialBalance, String accountHolder) {
        this.username = username;
        this.password = password;
        this.userAccount = new Account(accountHolder, bank, initialBalance);
    }

    /**
     * Get this user's username
     * @return username(user ID) of this user
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Get this user's password
     * @return the password for this user account
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Get user's account
     * @return the account owned by this user
     */
    @Override
    public Account getUserAccount() {
        return userAccount;
    }

}
