package use_case.ViewHistory;

import entity.Account;
import entity.ExchangeHistory;

import java.util.List;

public class ViewExchangeHistory {
    private final Account account;

    // Constructor
    public ViewExchangeHistory(Account account) {
        this.account = account;
    }

    // Method to view the exchange history
    public void viewExchangeHistory() {
        List<ExchangeHistory> histories = account.getExchangeHistory();

        if (histories.isEmpty()) {
            System.out.println("No exchange history found.");
            return;
        }

        for (ExchangeHistory history : histories) {
            System.out.println("Source Currency: " + history.getSourceCurrency());
            System.out.println("Target Currency: " + history.getTargetCurrency());
            System.out.println("Exchanged Amount: " + history.getExchangedAmount());
            System.out.println("Exchange Rate: " + history.getExchangeRate());
            System.out.println("Exchange Time: " + history.getExchangeTime());
            System.out.println("----------------------------");
        }
    }
}

