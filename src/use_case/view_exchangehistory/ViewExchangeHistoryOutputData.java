package use_case.view_exchangehistory;

import entity.ExchangeHistory;
import java.util.List;

public class ViewExchangeHistoryOutputData {
    private final List<ExchangeHistory> exchangeHistories;

    public ViewExchangeHistoryOutputData(List<ExchangeHistory> exchangeHistories) {
        this.exchangeHistories = exchangeHistories;
    }

    public List<ExchangeHistory> getExchangeHistories() {
        return exchangeHistories;
    }
}
