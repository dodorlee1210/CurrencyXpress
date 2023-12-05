package interface_adapter.account;

public class AccountState {
    private String username;
    private String method;
    private String bank;
    private String balance;
    private String[] currencies;


    public AccountState(AccountState copy) {
        username = copy.username;
        method = copy.method;
        bank = copy.bank;
        balance = copy.balance;
        currencies = copy.currencies;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public AccountState() {}

    public String getUsername() {
        return username;
    }

    public String getMethod() {
        return method;
    }

    public String getBank() {
        return bank;
    }

    public String getBalance() {
        return balance;
    }

    public String[] getCurrencies() {
        return currencies;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public void setCurrencies(String[] currencies) {
        this.currencies = currencies;
    }
}
