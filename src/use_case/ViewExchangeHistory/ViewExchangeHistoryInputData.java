package use_case.ViewExchangeHistory;

public class ViewExchangeHistoryInputData {
    final private String accountHolder;

    public ViewExchangeHistoryInputData(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    String getAccountHolder() {
        return accountHolder;
    }
}
