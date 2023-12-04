package interface_adapter.view_exchangehistory;

import interface_adapter.ViewManagerModel;
import use_case.view_exchangehistory.ViewExchangeHistoryOutputBoundary;
import use_case.view_exchangehistory.ViewExchangeHistoryOutputData;

public class ViewExchangeHistoryPresenter implements ViewExchangeHistoryOutputBoundary {

    private final ViewExchangeHistoryViewModel viewExchangeHistoryViewModel;
    private ViewManagerModel viewManagerModel;

    public ViewExchangeHistoryPresenter(ViewManagerModel viewManagerModel, ViewExchangeHistoryViewModel viewExchangeHistoryViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewExchangeHistoryViewModel = viewExchangeHistoryViewModel;
    }

    @Override
    public void prepareSuccessView(ViewExchangeHistoryOutputData response) {
        // On success, update the view with the exchange history data

        ViewExchangeHistoryState viewExchangeHistoryState = viewExchangeHistoryViewModel.getState();
        viewExchangeHistoryState.setExchangeHistory(response.getExchangeHistories());
        this.viewExchangeHistoryViewModel.setState(viewExchangeHistoryState);
        this.viewExchangeHistoryViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(viewExchangeHistoryViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        ViewExchangeHistoryState viewExchangeHistoryState = viewExchangeHistoryViewModel.getState();
        viewExchangeHistoryState.setError(error);
        viewExchangeHistoryViewModel.setState(viewExchangeHistoryState);
        viewExchangeHistoryViewModel.firePropertyChanged();
    }
}
