package interface_adapter.account;

import use_case.account.AccountInputBoundary;
import use_case.account.AccountInputData;

public class AccountController {
    final AccountInputBoundary accountInteractor;
    public AccountController(AccountInputBoundary accountInteractor) {
        this.accountInteractor = accountInteractor;
    }

    public void execute(String username, String method, String bank) {
        AccountInputData accountInputData = new AccountInputData(username, method, bank);
        accountInteractor.execute(accountInputData);
    }
}
