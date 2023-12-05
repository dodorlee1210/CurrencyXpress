package interface_adapter.account;

public class AccountState {
    private String username;
    private String method;

    public AccountState(AccountState copy) {

        username = copy.username;
        method = copy.method;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public AccountState() {
    }

    public String getUsername() {
        return username;
    }

    public String getMethod() {
        return method;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
