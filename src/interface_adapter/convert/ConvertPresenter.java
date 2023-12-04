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
        // On success, switch to the result popup view.
//        System.out.println(response.getCurrencyA() + response.getSymbolA() + String.valueOf(response.getLeftAmount()));
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
        if (error.contains("(B)")) {
            convertState.setSymbolBError(error);
        } else if (error.contains("non-numerical")) {
            convertState.setCurrencyBError(error);
        } else {
            convertState.setSymbolAError(error);
        }
        convertViewModel.firePropertyChanged();
    }


}
