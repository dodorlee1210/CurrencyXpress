package use_case.ViewExchangeHistory;

import entity.Account;

public interface ViewExchangeHistoryDataAccessInterface {
    boolean existsByName(String identifier);

    void save(Account ExchangeHistory);

    Account get(String username);
}
