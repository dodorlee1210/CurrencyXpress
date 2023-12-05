package interface_adapter.account;

import interface_adapter.ViewManagerModel;
import interface_adapter.convert.ConvertState;
import interface_adapter.convert.ConvertViewModel;
import use_case.account.AccountOutputBoundary;
import use_case.account.AccountOutputData;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
import use_case.account.AccountInteractor;
import interface_adapter.search_exchangerate.SearchState;
import interface_adapter.search_exchangerate.SearchViewModel;
public class AccountPresenter implements AccountOutputBoundary {
    private final AccountViewModel accountViewModel;
    private final ConvertViewModel convertViewModel;
    private LoginViewModel loginViewModel;
    private  SearchViewModel searchViewModel;
    private ViewManagerModel viewManagerModel;

    public AccountPresenter(ViewManagerModel viewManagerModel, AccountViewModel accountViewModel,
                            ConvertViewModel convertViewModel, LoginViewModel loginViewModel, SearchViewModel searchViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.accountViewModel = accountViewModel;
        this.convertViewModel = convertViewModel;
        this.loginViewModel = loginViewModel;
        this.searchViewModel = searchViewModel;
    }

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
        } else if (response.getMethod().equals("search")) {
            SearchState searchState = searchViewModel.getState();
            searchState.setUsername(response.getUsername());
            this.searchViewModel.setState(searchState);
            this.searchViewModel.firePropertyChanged();
            viewManagerModel.setActiveView(searchViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        }


    }

}
