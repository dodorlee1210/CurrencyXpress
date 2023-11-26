package use_case.convert;

import entity.Bank;
import use_case.login.LoginInputBoundary;

public class ConvertInteractor implements ConvertInputBoundary {

    final ConvertDataAccessInterface dataAccessObject;
    final ConvertOutputBoundary convertPresenter;

    ConvertInteractor(ConvertDataAccessInterface dataAccessInterface, ConvertOutputBoundary convertOutputBoundary) {
        this.dataAccessObject = dataAccessInterface;
        this.convertPresenter = convertOutputBoundary;

    }

    @Override
    public void execute(ConvertInputData convertInputData) {
        String symbolB = convertInputData.getSymbolB();
        String currencyB = convertInputData.getCurrencyB();
        String symbolA = convertInputData.getSymbolA();

        if (!dataAccessObject.existsByCode(symbolB)) {
            convertPresenter.prepareFailView(symbolB + ": Currency Code does not exist.");
        } else if (!dataAccessObject.existsByCode(symbolA)) {
            convertPresenter.prepareFailView(symbolA + ": Currency Code does not exist.");
        } else {
            if (symbolB.equals(symbolA)) {
                convertPresenter.prepareFailView("Exchange does not happen for same currency codes.");
            } else {
                // NO entity that stores individual rates, where to pull results from?
//                 = dataAccessObject.get();
//                ConvertOutputData convertOutputData = new ConvertOutputData(symbolA, currencyA, false);
//                convertPresenter.prepareSuccessView(convertOutputData);
            }
        }

    }
}
