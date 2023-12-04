package use_case.convert;

import data_access.FileUserDataAccessObject;
import entity.banks.Bank;

public class ConvertInteractor implements ConvertInputBoundary {

    final ConvertDataAccessInterface convertDataAccessInterface;
    final ConvertOutputBoundary convertPresenter;
    FileUserDataAccessObject dataAccessObject;

    public ConvertInteractor(ConvertDataAccessInterface convertDataAccessInterface,
                             ConvertOutputBoundary convertOutputBoundary, FileUserDataAccessObject dataAccessObject) {
        this.dataAccessObject = dataAccessObject;
        this.convertPresenter = convertOutputBoundary;
        this.convertDataAccessInterface = convertDataAccessInterface;
    }

    @Override
    public void execute(ConvertInputData convertInputData) {
        String symbolB = convertInputData.getSymbolB();
        String currencyB = convertInputData.getCurrencyB();
        String symbolA = convertInputData.getSymbolA();

        if (!convertDataAccessInterface.existsByCode(symbolB)) {
            convertPresenter.prepareFailView(symbolB + ": Currency Code does not exist (B).");
        } else if (!currencyB.matches("\\d+(\\.\\d+)?")) {
            convertPresenter.prepareFailView(currencyB + ": Currency has non-numerical values in it.");
        } else if (!convertDataAccessInterface.existsByCode(symbolA)) {
            convertPresenter.prepareFailView(symbolA + ": Currency Code does not exist (A).");
        } else {
            if (symbolB.equals(symbolA)) {
                convertPresenter.prepareFailView("Exchange does not happen for same currency codes.");
            } else {
                String[] currency = convertDataAccessInterface.get(symbolA).split(":");
//                User user = dataAccessObject.get(convertInputData);
//                Bank bank = user.
//                String exchangedAmount = convertDataAccessInterface.calculateExchange(currencyB, currency[1], user.bank());
                ConvertOutputData convertOutputData = new ConvertOutputData(currency[0], currency[1], false);
                convertPresenter.prepareSuccessView(convertOutputData);
            }
        }

    }
}
