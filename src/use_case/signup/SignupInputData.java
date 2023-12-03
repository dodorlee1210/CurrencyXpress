package use_case.signup;

import entity.banks.Bank;

public class SignupInputData {

    final private String username;
    final private String password;
    final private String repeatPassword;
    private String bankName;
    private double initialBalance;
    private String accountHolder;

    public SignupInputData(String username, String password, String repeatPassword,
                           String bankName, double initialBalance, String accountHolder) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.accountHolder = accountHolder;
        this.bankName = bankName;
        this.initialBalance = initialBalance;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getBankName() {
        return bankName;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }
}
