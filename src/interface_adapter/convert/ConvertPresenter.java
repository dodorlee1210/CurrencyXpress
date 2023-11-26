package interface_adapter.convert;

import interface_adapter.ViewManagerModel;
import use_case.convert.ConvertOutputBoundary;
import use_case.convert.ConvertOutputData;

public class ConvertPresenter implements ConvertOutputBoundary {

    private final ConvertViewModel convertViewModel;
    private ViewManagerModel viewManagerModel;

    public ConvertPresenter(ViewManagerModel viewManagerModel, ConvertViewModel convertViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.convertViewModel = convertViewModel;
    }

    @Override
    public void prepareSuccessView(ConvertOutputData response) {
        // On success, switch to the logged in view.

        ConvertState convertState = convertViewModel.getState();
        convertState.setCurrencyA(response.getCurrencyA());
        convertState.setSymbolA(response.getSymbolA());
        this.convertViewModel.setState(convertState);
        this.convertViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(convertViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        ConvertState convertState = convertViewModel.getState();
        // 3 errors exist => conditional?
//        convertState.set?Error(error);
        convertViewModel.firePropertyChanged();
    }
}
