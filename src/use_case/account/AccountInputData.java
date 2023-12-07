package use_case.account;


public class AccountInputData {

    final private String username;
    private String method;
    private String bank;

    public AccountInputData(String username, String method, String bank) {
        this.username = username;
        this.method = method;
        this.bank = bank;
    }

    String getUsername() {
        return username;
    }

    public String getMethod() {
        return method;
    }

    public String getBank() {
        return bank;
    }
}
