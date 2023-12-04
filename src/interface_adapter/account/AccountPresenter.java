package interface_adapter.account;

import interface_adapter.ViewManagerModel;
import interface_adapter.convert.ConvertViewModel;
import use_case.signup.SignupOutputData;

public class AccountPresenter {
    private final AccountViewModel accountViewModel;
    private final ConvertViewModel convertViewModel;
    private ViewManagerModel viewManagerModel;

    public AccountPresenter(ViewManagerModel viewManagerModel, AccountViewModel accountViewModel,
                            ConvertViewModel convertViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.accountViewModel = accountViewModel;
        this.convertViewModel = convertViewModel;
    }

    public void prepareConvertView() {
        // On success, switch to the convert view.
        convertViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(convertViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

//    public void prepareLogOutView(String error) {
//        SignupState signupState = signupViewModel.getState();
//        signupState.setUsernameError(error);
//        signupViewModel.firePropertyChanged();
//    }
}
