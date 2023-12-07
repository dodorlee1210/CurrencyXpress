package use_case.account;

import entity.banks.*;

public class AccountInteractor implements AccountInputBoundary{
    final AccountOutputBoundary accountPresenter;

    public AccountInteractor(AccountOutputBoundary accountPresenter) {
        this.accountPresenter = accountPresenter;
    }

    public void execute(AccountInputData accountInputData) {
        String bank = accountInputData.getBank();
        String fee = "NA";

        if (bank.equals("BMO")) {
            fee = String.valueOf(new BMO().getExchangeServiceFee() * 100);
        } else if (bank.equals("CIBC")) {
            fee = String.valueOf(new CIBC().getExchangeServiceFee() * 100);
        } else if (bank.equals("RBC")) {
            fee = String.valueOf(new RBC().getExchangeServiceFee() * 100);
        } else if (bank.equals("Scotia")) {
            fee = String.valueOf(new Scotia().getExchangeServiceFee() * 100);
        } else if (bank.equals("TD")) {
            fee = String.valueOf(new TD().getExchangeServiceFee() * 100);
        }

        AccountOutputData accountOutputData = new AccountOutputData(accountInputData.getUsername(),
                accountInputData.getMethod(), fee + "%", false);
        accountPresenter.prepareSuccessView(accountOutputData);
    }
}
