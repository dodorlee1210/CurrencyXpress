package use_case.search_exchangerate;

import data_access.FileUserDataAccessObject;

public class SearchInteractor implements SearchInputBoundary {

    final SearchDataAccessInterface searchDataAccessObject;

    final SearchOutputBoundary searchPresenter;

    FileUserDataAccessObject dataAccessObject;
    String exchangeResult;


    public SearchInteractor(SearchDataAccessInterface searchDataAccessObject, SearchOutputBoundary searchOutputBoundary,
                            FileUserDataAccessObject dataAccessObject) {
        this.searchDataAccessObject = searchDataAccessObject;
        this.searchPresenter = searchOutputBoundary;
        this.dataAccessObject = dataAccessObject;
    }

    public String execute(SearchInputData searchInputData) {
        String date = searchInputData.getDate();
        String baseCurrency = searchInputData.getBaseCurrency();
        String symbols = searchInputData.getSymbols();

        if (!searchDataAccessObject.existsByCode(date)) {
            searchPresenter.prepareFailView(date + ": This date is not a valid date.");
            exchangeResult = "Error:" + " This date is not a valid date.";
        } else if (!searchDataAccessObject.existsByCode(baseCurrency)) {
            searchPresenter.prepareFailView(baseCurrency + ": Currency Code is not valid as a base currency.");
            exchangeResult = "Error:" + " Currency code is not valid as a base currency.";
        } else {
            if (baseCurrency.equals(symbols)){
                searchPresenter.prepareFailView("Exchange does not happen for same currency codes.");
                exchangeResult = "Error:" + " Exchange does not happen for same currency codes.";
            } else {
                String[] currency = searchDataAccessObject.get(symbols).split(":");
                SearchOutputData searchOutputData = new SearchOutputData(currency[0],currency[1],false);
                searchPresenter.prepareSuccessView(searchOutputData);

                exchangeResult = searchOutputData.get_afterSymbols() + "\n"
                        + searchOutputData.get_afterCurrency();
            }

        }
        return exchangeResult;
    }
}
