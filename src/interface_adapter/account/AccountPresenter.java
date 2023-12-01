package interface_adapter.account;

import interface_adapter.ViewManagerModel;
import interface_adapter.convert.ConvertState;
import interface_adapter.convert.ConvertViewModel;
import use_case.account.AccountOutputBoundary;

public class AccountPresenter implements AccountOutputBoundary {
    private final AccountViewModel accountViewModel;
    private final ViewManagerModel viewManagerModel;
    private final ConvertViewModel convertViewModel;

    public AccountPresenter(ViewManagerModel viewManagerModel, AccountViewModel accountViewModel, ConvertViewModel convertViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.accountViewModel = accountViewModel;
        this.convertViewModel = convertViewModel;
    }

    @Override
    public void prepareSuccessView() {
        // On success, switch to convert view

        ConvertState convertState = convertViewModel.getState();
        this.convertViewModel.setState(convertState);
        this.convertViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(convertViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }


    @Override
    public void prepareFailView() {
        AccountState accountState = accountViewModel.getState();
        convertViewModel.firePropertyChanged();
    }
}
