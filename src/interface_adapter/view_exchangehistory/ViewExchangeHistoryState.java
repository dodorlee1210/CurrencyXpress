package interface_adapter.view_exchangehistory;

import entity.ExchangeHistory;
import java.util.List;

public class ViewExchangeHistoryState {
    private List<ExchangeHistory> exchangeHistory = null;
    private String error = null;

    public ViewExchangeHistoryState(ViewExchangeHistoryState copy) {
        exchangeHistory = copy.exchangeHistory;
        error = copy.error;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public ViewExchangeHistoryState() {}

    public List<ExchangeHistory> getExchangeHistory() {
        return exchangeHistory;
    }

    public String getError() {
        return error;
    }

    public void setExchangeHistory(List<ExchangeHistory> exchangeHistory) {
        this.exchangeHistory = exchangeHistory;
    }

    public void setError(String error) {
        this.error = error;
    }
}
