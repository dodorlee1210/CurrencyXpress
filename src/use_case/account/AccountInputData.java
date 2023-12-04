package use_case.account;


public class AccountInputData {

    final private String username;

    public AccountInputData(String username) {
        this.username = username;
    }

    String getUsername() {
        return username;
    }
}
