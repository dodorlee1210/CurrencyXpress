package interface_adapter.view_exchangehistory;

import use_case.view_exchangehistory.ViewExchangeHistoryInputBoundary;
import use_case.view_exchangehistory.ViewExchangeHistoryInputData;

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
