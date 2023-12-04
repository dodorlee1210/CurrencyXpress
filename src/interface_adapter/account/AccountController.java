package interface_adapter.account;

import use_case.account.AccountInteractor;

public class AccountController {
    final AccountInteractor accountInteractor;
    public AccountController(AccountInteractor accountInteractor) {
        this.accountInteractor = accountInteractor;
    }


    public void execute() {
        accountInteractor.execute();
    }
}
