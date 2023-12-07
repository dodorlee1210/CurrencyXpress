package interface_adapter.account;

import interface_adapter.ViewManagerModel;
import interface_adapter.convert.ConvertState;
import interface_adapter.convert.ConvertViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.search_exchangerate.SearchViewModel;
import use_case.account.AccountOutputBoundary;
import use_case.account.AccountOutputData;
import interface_adapter.search_exchangerate.SearchState;
public class AccountPresenter implements AccountOutputBoundary {
    private final AccountViewModel accountViewModel;
    private final ConvertViewModel convertViewModel;
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;
    private SearchViewModel searchViewModel;

    public AccountPresenter(ViewManagerModel viewManagerModel, AccountViewModel accountViewModel,
                            ConvertViewModel convertViewModel, LoginViewModel loginViewModel, SearchViewModel searchViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.accountViewModel = accountViewModel;
        this.convertViewModel = convertViewModel;
        this.loginViewModel = loginViewModel;
        this.searchViewModel = searchViewModel;
    }

    @Override
    public void prepareSuccessView(AccountOutputData response) {
        if (response.getMethod().equals("logout")) {
            // Reset textfields/input for login page
            LoginState loginState = loginViewModel.getState();
            loginState.setUsername("");
            loginState.setPassword("");
            loginViewModel.setState(loginState);
            loginViewModel.firePropertyChanged();

            viewManagerModel.setActiveView(loginViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        } else if (response.getMethod().equals("exchange")) {
            // Switch to convert page
            ConvertState convertState = convertViewModel.getState();
            convertState.setUsername(response.getUsername());
            convertState.setExchangeBankFee(response.getBankFee());
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