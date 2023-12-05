package use_case.account;

public class AccountOutputData {

    private final String username;
    private String method;
    private boolean useCaseFailed;

    public AccountOutputData(String username, String method, boolean useCaseFailed) {
        this.username = username;
        this.method = method;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }
    public String getMethod(){
        return method;
    }
}
