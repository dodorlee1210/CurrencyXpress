package app;

import entity.Bank;
import interface_adapter.ViewManagerModel;
import interface_adapter.convert.ConvertController;
import interface_adapter.convert.ConvertPresenter;
import interface_adapter.convert.ConvertViewModel;
import use_case.convert.ConvertDataAccessInterface;
import use_case.convert.ConvertInputBoundary;
import use_case.convert.ConvertInteractor;
import use_case.convert.ConvertOutputBoundary;
import view.ConvertView;

import javax.swing.*;
import java.io.IOException;

public class ConvertUseCaseFactory {

    private ConvertUseCaseFactory() {}

    public static ConvertView create(ViewManagerModel viewManagerModel,
                                     ConvertViewModel convertViewModel,
                                     ConvertDataAccessInterface convertDataAccessInterface){
        try {
            ConvertController convertController = createConvertUseCase(viewManagerModel, convertViewModel, convertDataAccessInterface);
            return new ConvertView(convertViewModel, convertController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error in Factory");
        }
        return null;
    }

    private static ConvertController createConvertUseCase(ViewManagerModel viewManagerModel,
                                                          ConvertViewModel convertViewModel,
                                                          ConvertDataAccessInterface convertDataAccessInterface) throws IOException {
        ConvertOutputBoundary convertOutputBoundary = new ConvertPresenter(viewManagerModel, convertViewModel);
        ConvertInputBoundary convertInteractor = new ConvertInteractor(convertDataAccessInterface, convertOutputBoundary, new Bank());
        return new ConvertController(convertInteractor);
    }
}
