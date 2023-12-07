package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final AccountViewModel accountViewModel;
    private ViewManagerModel viewManagerModel;
    private SignupViewModel signupViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          AccountViewModel accountViewModel,
                          LoginViewModel loginViewModel,
                          SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.accountViewModel = accountViewModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        if (response.getUsername().equals("cancelgobacktosignuppage2023")) {
            // Go back to signup page
            this.signupViewModel.firePropertyChanged();

            this.viewManagerModel.setActiveView(signupViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        } else {
            // Switch to account view
            AccountState loggedInState = accountViewModel.getState();
            loggedInState.setUsername(response.getUsername());
            loggedInState.setBank(response.getBank());
            loggedInState.setBalance(String.valueOf(response.getBalance()));
            this.accountViewModel.setState(loggedInState);
            this.accountViewModel.firePropertyChanged();

            this.viewManagerModel.setActiveView(accountViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        }

    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}
