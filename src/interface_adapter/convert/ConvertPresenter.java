package interface_adapter.convert;

import interface_adapter.ViewManagerModel;
import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;
import use_case.convert.ConvertOutputBoundary;
import use_case.convert.ConvertOutputData;

public class ConvertPresenter implements ConvertOutputBoundary {

    private final ConvertViewModel convertViewModel;
    private ViewManagerModel viewManagerModel;
    private AccountViewModel accountViewModel;

    public ConvertPresenter(ViewManagerModel viewManagerModel,
                            ConvertViewModel convertViewModel,
                            AccountViewModel accountViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.convertViewModel = convertViewModel;
        this.accountViewModel = accountViewModel;
    }

    @Override
    public void prepareSuccessView(ConvertOutputData response) {
        // On success, switch to the result popup view.
        ConvertState convertState = convertViewModel.getState();

        if (response.getSymbolA().equals("HOME")) {
            // Reset textfields/input to be emtpy
            convertState.setSymbolB("");
            convertState.setCurrencyB("");
            convertState.setSymbolA("");
            this.convertViewModel.setState(convertState);
            this.convertViewModel.firePropertyChanged();

            // Go back to account view
            this.viewManagerModel.setActiveView(accountViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        } else {
            AccountState accountState = accountViewModel.getState();
            accountState.setBalance(String.valueOf(response.getLeftAmount()));
//            accountState.setCurrencies(response.getCurrencies);
            accountViewModel.firePropertyChanged();

            convertState.setCurrencyA(response.getCurrencyA());
            convertState.setSymbolA(response.getSymbolA());
            this.convertViewModel.setState(convertState);
            this.convertViewModel.firePropertyChanged();

            this.viewManagerModel.setActiveView(convertViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        }
    }

    @Override
    public void prepareFailView(String error) {
        ConvertState convertState = convertViewModel.getState();
        if (error.contains("(B)")) {
            convertState.setSymbolBError(error);
        } else if (error.contains("non-numerical")) {
            convertState.setCurrencyBError(error);
        } else {
            convertState.setSymbolAError(error);
        }
        convertViewModel.firePropertyChanged();
    }


}
