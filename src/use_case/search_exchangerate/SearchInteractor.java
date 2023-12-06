package use_case.search_exchangerate;

public class SearchInteractor implements SearchInputBoundary {

    final SearchDataAccessInterface dataAccessObject;

    final SearchOutputBoundary searchPresenter;


    public SearchInteractor(SearchDataAccessInterface dataAccessObject, SearchOutputBoundary searchOutputBoundary) {
        this.dataAccessObject = dataAccessObject;
        this.searchPresenter = searchOutputBoundary;
    }

    public void execute(SearchInputData searchInputData) {
        String date = searchInputData.getDate();
        String baseCurrency = searchInputData.getBaseCurrency();
        String symbols = searchInputData.getSymbols();

        if (!dataAccessObject.existsByCode(date)) {
            searchPresenter.prepareFailView(date + ": This date is not a valid date.");
        } else if (!dataAccessObject.existsByCode(baseCurrency)) {
            searchPresenter.prepareFailView(baseCurrency + ": Currency Code is not valid as a base currency.");
        } else {
            if (baseCurrency.equals(symbols)){
                searchPresenter.prepareFailView("Exchange does not happen for same currency codes.");
            } else {
                String[] currency = dataAccessObject.get(symbols).split(":");
                searchPresenter.prepareSuccessView(new SearchOutputData(currency[0],currency[1],false));
            }

        }

    }
}
