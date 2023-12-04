package interface_adapter.account;

import interface_adapter.ViewManagerModel;
import interface_adapter.convert.ConvertState;
import interface_adapter.convert.ConvertViewModel;
import use_case.account.AccountOutputBoundary;
import use_case.account.AccountOutputData;

public class AccountPresenter implements AccountOutputBoundary {
    private final AccountViewModel accountViewModel;
    private final ConvertViewModel convertViewModel;
    private ViewManagerModel viewManagerModel;

    public AccountPresenter(ViewManagerModel viewManagerModel, AccountViewModel accountViewModel,
                            ConvertViewModel convertViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.accountViewModel = accountViewModel;
        this.convertViewModel = convertViewModel;
    }

    public void prepareConvertView(AccountOutputData response) {
        // On success, switch to the convert view.
        ConvertState convertState = convertViewModel.getState();
        convertState.setUsername(response.getUsername());
        this.convertViewModel.setState(convertState);
        this.convertViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(convertViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

//    public void prepareLogOutView(String error) {
//        SignupState signupState = signupViewModel.getState();
//        signupState.setUsernameError(error);
//        signupViewModel.firePropertyChanged();
//    }
}
