package use_case.view_exchangehistory;

public interface ViewExchangeHistoryOutputBoundary {
    void prepareSuccessView(ViewExchangeHistoryOutputData View);

    void prepareFailView(String error);
}
