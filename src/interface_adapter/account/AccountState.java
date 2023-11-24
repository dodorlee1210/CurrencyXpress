package interface_adapter.account;

public class AccountState {
    private String username = "";

    public AccountState(AccountState copy) {
        username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public AccountState() {}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
