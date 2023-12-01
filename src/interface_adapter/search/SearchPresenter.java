package interface_adapter.search;

import interface_adapter.ViewManagerModel;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;

public class SearchPresenter implements SearchOutputBoundary{
    private final SearchViewModel searchViewModel;
    private ViewManagerModel viewManagerModel;

    public SearchPresenter(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchViewModel = searchViewModel;
    }

    @Override
    public void prepareSuccessView(SearchOutputData response) {
        // On success, switch to the logged in view.

        SearchState searchState = searchViewModel.getState();
        searchState.setAfterCurrency(response.get_afterCurrency());
        searchState.setAfterSymbols(response.get_afterSymbols());
        this.searchViewModel.setState(searchState);
        this.searchViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(searchViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SearchState searchState = searchViewModel.getState();
        if (error.contains("date")) {
            searchState.setDateError(error);
        } else if (error.contains("base")) {
            searchState.setBaseCurrencyError(error);
        } else {
            searchState.setSymbolsError(error);
        }
        searchViewModel.firePropertyChanged();
    }
}
