package use_case.account;

import interface_adapter.account.AccountPresenter;
import use_case.signup.SignupInputData;

public class AccountInteractor implements AccountInputBoundary{
    final AccountOutputBoundary accountPresenter;

    public AccountInteractor(AccountOutputBoundary accountPresenter) {
        this.accountPresenter = accountPresenter;
    }

    public void execute(AccountInputData accountInputData) {
        AccountOutputData accountOutputData = new AccountOutputData(accountInputData.getUsername(), false);
        accountPresenter.prepareConvertView(accountOutputData);
    }
}
