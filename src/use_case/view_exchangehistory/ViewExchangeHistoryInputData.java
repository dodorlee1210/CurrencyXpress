package use_case.view_exchangehistory;

public class ViewExchangeHistoryInputData {
    final private String accountHolder;

    public ViewExchangeHistoryInputData(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getAccountHolder() {
        return accountHolder;
    }
}
