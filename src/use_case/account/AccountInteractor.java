package use_case.account;

import interface_adapter.account.AccountPresenter;
import use_case.signup.SignupInputData;

public class AccountInteractor {
    final AccountPresenter accountPresenter;

    public AccountInteractor(AccountPresenter accountPresenter) {
        this.accountPresenter = accountPresenter;
    }

    public void execute() {
        accountPresenter.prepareConvertView();
    }
}
