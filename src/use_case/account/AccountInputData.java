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

    /**
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the name of the button which represents the actions to be performed
     */
    public String getMethod() {
        return method;
    }

    /**
     * @return the bank name
     */
    public String getBank() {
        return bank;
    }
}
