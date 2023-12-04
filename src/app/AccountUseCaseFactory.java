package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.account.AccountController;
import interface_adapter.account.AccountPresenter;
import interface_adapter.account.AccountViewModel;
import interface_adapter.convert.ConvertViewModel;
import use_case.account.AccountInputBoundary;
import use_case.account.AccountInteractor;
import use_case.account.AccountOutputBoundary;
import view.AccountView;

import javax.swing.*;
import java.io.IOException;

public class AccountUseCaseFactory {
    /** Prevent instantiation. */
    private AccountUseCaseFactory() {}

    public static AccountView create(ViewManagerModel viewManagerModel,
                                     AccountViewModel accountViewModel,
                                     ConvertViewModel convertViewModel) {
        try {
            AccountController accountController = createAccountUseCase(viewManagerModel, accountViewModel, convertViewModel);
            return new AccountView(accountViewModel, accountController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }
    private static AccountController createAccountUseCase(
            ViewManagerModel viewManagerModel,
            AccountViewModel accountViewModel,
            ConvertViewModel convertViewModel) throws IOException {

        AccountOutputBoundary accountOutputBoundary = new AccountPresenter(viewManagerModel, accountViewModel, convertViewModel);

        AccountInputBoundary accountInteractor = new AccountInteractor(accountOutputBoundary);

        return new AccountController(accountInteractor);
    }
}
