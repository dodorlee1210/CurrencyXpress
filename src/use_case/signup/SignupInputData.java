package use_case.signup;

import entity.banks.Bank;

public class SignupInputData {

    final private String username;
    final private String password;
    final private String repeatPassword;
    private Bank bank;
    private double initialBalance;
    private String accountHolder;

    private String pass;

    public SignupInputData(String username, String password, String repeatPassword,
                           Bank bank, double initialBalance, String accountHolder, String pass) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.accountHolder = accountHolder;
        this.bank = bank;
        this.initialBalance = initialBalance;
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public Bank getBank() {
        return bank;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getPass() {
        return pass;
    }
}
