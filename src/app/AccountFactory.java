package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.account.AccountController;
import interface_adapter.account.AccountPresenter;
import interface_adapter.account.AccountViewModel;
import interface_adapter.convert.ConvertViewModel;
import use_case.account.AccountInteractor;
import view.AccountView;

import javax.swing.*;
import java.io.IOException;

public class AccountFactory {
    /** Prevent instantiation. */
    private AccountFactory() {}

    public static AccountView create(ViewManagerModel viewManagerModel,
                                     AccountViewModel accountViewModel, ConvertViewModel convertViewModel) {
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
            AccountViewModel accountViewModel, ConvertViewModel convertViewModel) throws IOException {

        AccountPresenter accountPresenter = new AccountPresenter(viewManagerModel, accountViewModel, convertViewModel);
        AccountInteractor accountInteractor = new AccountInteractor(accountPresenter);

        return new AccountController(accountInteractor);
    }
}
