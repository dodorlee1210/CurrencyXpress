package use_case.login;

public class LoginOutputData {

    private final String username;
    private boolean useCaseFailed;
    String bank;
    double balance;
    String[][] currencies;

    public LoginOutputData(String username, String bank, double balance, String[][] currencies, boolean useCaseFailed) {
        this.username = username;
        this.bank = bank;
        this.balance = balance;
        this.currencies = currencies;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public String[][] getCurrencies() {
        return currencies;
    }

    public String getBank() {
        return bank;
    }

    public double getBalance() {
        return balance;
    }
}
