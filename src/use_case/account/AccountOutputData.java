package use_case.account;

public class AccountOutputData {

    private final String username;
    private boolean useCaseFailed;

    public AccountOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }
}
