package use_case.view_exchangehistory;

import entity.Account;

public interface ViewExchangeHistoryDataAccessInterface {
    boolean existsByName(String identifier);

    void save(Account ExchangeHistory);

    Account get(String username);
}
