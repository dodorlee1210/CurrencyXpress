package interface_adapter.account;

import interface_adapter.ViewManagerModel;
import interface_adapter.convert.ConvertState;
import interface_adapter.convert.ConvertViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.account.AccountInteractor;
import use_case.account.AccountOutputBoundary;
import use_case.account.AccountOutputData;

public class AccountPresenter implements AccountOutputBoundary {
    private final AccountViewModel accountViewModel;
    private final ConvertViewModel convertViewModel;
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;

    public AccountPresenter(ViewManagerModel viewManagerModel, AccountViewModel accountViewModel,
                            ConvertViewModel convertViewModel, LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.accountViewModel = accountViewModel;
        this.convertViewModel = convertViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(AccountOutputData response) {
        if (response.getMethod().equals("logout")) {
            LoginState loginState = loginViewModel.getState();
            loginState.setUsername(response.getUsername());
            loginViewModel.setState(loginState);
            loginViewModel.firePropertyChanged();
            viewManagerModel.setActiveView(loginViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        } else if (response.getMethod().equals("exchange")) {
            ConvertState convertState = convertViewModel.getState();
            convertState.setUsername(response.getUsername());
            this.convertViewModel.setState(convertState);
            this.convertViewModel.firePropertyChanged();

            viewManagerModel.setActiveView(convertViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        }
    }
}
