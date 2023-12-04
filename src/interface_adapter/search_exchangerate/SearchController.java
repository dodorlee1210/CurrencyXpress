package interface_adapter.search_exchangerate;

import use_case.search_exchangerate.SearchInputBoundary;
import use_case.search_exchangerate.SearchInputData;

public class SearchController {

    final SearchInputBoundary searchUseCaseInteractor;

    public SearchController(SearchInputBoundary searchUseCaseInteractor){
        this.searchUseCaseInteractor = searchUseCaseInteractor;
    }
    public void execute(String date, String baseCurrency, String symbols){
        SearchInputData searchInputData = new SearchInputData(date, baseCurrency, symbols);

        searchUseCaseInteractor.execute(searchInputData);
    }
}
