package interface_adapter.ViewExchangeHistory;

import interface_adapter.ViewExchangeHistory.ViewExchangeHistoryViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.ViewExchangeHistory.ViewExchangeHistoryState;
import use_case.ViewExchangeHistory.ViewExchangeHistoryOutputBoundary;
import use_case.ViewExchangeHistory.ViewExchangeHistoryOutputData;

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
