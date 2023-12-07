package use_case.account;

public class AccountOutputData {

    private final String username;
    public String bankFee;
    private String method;
    private boolean useCaseFailed;

    public AccountOutputData(String username, String method, String bankFee, boolean useCaseFailed) {
        this.username = username;
        this.method = method;
        this.bankFee = bankFee;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public String getMethod() {
        return method;
    }

    public String getBankFee() {
        return bankFee;
    }
}
