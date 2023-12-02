package interface_adapter.ViewExchangeHistory;

import use_case.ViewExchangeHistory.ViewExchangeHistoryInputBoundary;
import use_case.ViewExchangeHistory.ViewExchangeHistoryInputData;

public class ViewExchangeHistoryController {

    final ViewExchangeHistoryInputBoundary viewExchangeHistoryUseCaseInteractor;

    public ViewExchangeHistoryController(ViewExchangeHistoryInputBoundary viewExchangeHistoryUseCaseInteractor) {
        this.viewExchangeHistoryUseCaseInteractor = viewExchangeHistoryUseCaseInteractor;
    }

    public void execute(String accountHolder) {
        ViewExchangeHistoryInputData viewExchangeHistoryInputData = new ViewExchangeHistoryInputData(accountHolder);

        viewExchangeHistoryUseCaseInteractor.execute(viewExchangeHistoryInputData);
    }
}
