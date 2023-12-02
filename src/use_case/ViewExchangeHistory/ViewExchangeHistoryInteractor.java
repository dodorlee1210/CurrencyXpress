package use_case.ViewExchangeHistory;

import entity.ExchangeHistory;
import entity.Account;

import java.util.List;

// The Interactor class that implements the ViewExchangeHistoryInputBoundary interface
public class ViewExchangeHistoryInteractor implements ViewExchangeHistoryInputBoundary {

    // The DataAccessInterface and OutputBoundary needed by the interactor
    private final ViewExchangeHistoryDataAccessInterface viewExchangeHistoryDataAccessObject;
    private final ViewExchangeHistoryOutputBoundary viewExchangeHistoryPresenter;

    // Constructor that initializes the DataAccessInterface and OutputBoundary
    public ViewExchangeHistoryInteractor(ViewExchangeHistoryDataAccessInterface viewExchangeHistoryDataAccessObject, ViewExchangeHistoryOutputBoundary viewExchangeHistoryPresenter) {
        this.viewExchangeHistoryDataAccessObject = viewExchangeHistoryDataAccessObject;
        this.viewExchangeHistoryPresenter = viewExchangeHistoryPresenter;
    }

    // The execute method, which is called to view the exchange history
    @Override
    public void execute(ViewExchangeHistoryInputData viewExchangeHistoryInputData) {
        // Get the account holder's name from the input data
        String accountHolder = viewExchangeHistoryInputData.getAccountHolder();

        // Check if the account exists
        if(viewExchangeHistoryDataAccessObject.existsByName(accountHolder)) {
            // If the account exists, get it from the DataAccessObject
            Account Account = viewExchangeHistoryDataAccessObject.get(accountHolder);
            // Get the exchange history from the account
            List<ExchangeHistory> histories = Account.getExchangeHistory();

            // If the exchange history is empty, prepare a fail view with an error message
            if (histories.isEmpty()) {
                viewExchangeHistoryPresenter.prepareFailView("No exchange history found.");
            }
            // If the exchange history is not empty, prepare a success view with the exchange history data
            viewExchangeHistoryPresenter.prepareSuccessView(new ViewExchangeHistoryOutputData(Account.getExchangeHistory()));
        } else {
            // If the account does not exist, prepare a fail view with an error message
            viewExchangeHistoryPresenter.prepareFailView("Account does not exist.");
        }
    }
}
