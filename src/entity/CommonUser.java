package entity;

import entity.banks.Bank;

public class CommonUser implements User {

    private final String username;
    private final String password;
    private Account userAccount;

    CommonUser(String username, String password, String bankName, double initialBalance, String accountHolder) {
        this.username = username;
        this.password = password;
        this.userAccount = new Account(accountHolder, bankName, initialBalance);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Account getUserAccount() {
        return userAccount;
    }

}
