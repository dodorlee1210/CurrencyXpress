package use_case.login;

import entity.Account;
import entity.User;

public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();

        if (username.equals("cancelgobacktosignuppage2023")) {
            String[][] back = {{"back"}};
            loginPresenter.prepareSuccessView(new LoginOutputData(username, "",
                    0.0, back, false));
        } else if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        } else {
            User user = userDataAccessObject.get(loginInputData.getUsername());
            Account account = user.getUserAccount();
            String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
            } else {
                LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(), account.getBankName(),
                        account.getBalance(), account.getAllForeignCurrencies(), false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}