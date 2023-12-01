package use_case.ViewExchangeHistory;

public interface ViewExchangeHistoryOutputBoundary {
    void prepareSuccessView(ViewExchangeHistoryOutputData View);

    void prepareFailView(String error);
}
