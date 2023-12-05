package use_case.account;

public class AccountInteractor implements AccountInputBoundary{
    final AccountOutputBoundary accountPresenter;

    public AccountInteractor(AccountOutputBoundary accountPresenter) {
        this.accountPresenter = accountPresenter;
    }

    public void execute(AccountInputData accountInputData) {
        AccountOutputData accountOutputData = new AccountOutputData(accountInputData.getUsername(), false);
        accountPresenter.prepareSearchView(accountOutputData);
    }
}
