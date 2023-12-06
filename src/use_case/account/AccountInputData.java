package use_case.account;


public class AccountInputData {

    final private String username;
    private String method;


    public AccountInputData(String username, String method) {


        this.username = username;
        this.method = method;
    }

    String getUsername() {
        return username;
    }

    public String getMethod(){

        return method;
    }
}
