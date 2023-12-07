package interface_adapter.search_exchangerate;

import interface_adapter.ViewManagerModel;
import interface_adapter.account.AccountViewModel;
import interface_adapter.convert.ConvertState;
import use_case.search_exchangerate.SearchOutputBoundary;
import use_case.search_exchangerate.SearchOutputData;

public class SearchPresenter implements SearchOutputBoundary{
    private final SearchViewModel searchViewModel;
    private ViewManagerModel viewManagerModel;
    private AccountViewModel accountViewModel;

    public SearchPresenter(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, AccountViewModel accountViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchViewModel = searchViewModel;
        this.accountViewModel = accountViewModel;
    }

    @Override
    public void prepareSuccessView(SearchOutputData response) {
        // On success, switch to the logged in view.
        SearchState searchState = searchViewModel.getState();
        if (response.get_afterSymbols().equals("HOME")) {
            // Reset textfields/input to be emtpy
            searchState.setDate("");
            searchState.setBaseCurrency("");
            searchState.setSymbols("");
            this.searchViewModel.setState(searchState);
            this.searchViewModel.firePropertyChanged();

            // Go back to account view
            this.viewManagerModel.setActiveView(accountViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        } else {
        searchState.setAfterCurrency(response.get_afterCurrency());
        searchState.setAfterSymbols(response.get_afterSymbols());
        this.searchViewModel.setState(searchState);
        this.searchViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(searchViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }}

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


