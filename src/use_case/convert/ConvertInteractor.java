package use_case.convert;

public class ConvertInteractor implements ConvertInputBoundary {

    final ConvertDataAccessInterface dataAccessObject;
    final ConvertOutputBoundary convertPresenter;

    public ConvertInteractor(CurrencyConverter dataAccessInterface, ConvertOutputBoundary convertOutputBoundary) {
        this.dataAccessObject = dataAccessInterface;
        this.convertPresenter = convertOutputBoundary;

    }

    @Override
    public void execute(ConvertInputData convertInputData) {
        String symbolB = convertInputData.getSymbolB();
        String currencyB = convertInputData.getCurrencyB();
        String symbolA = convertInputData.getSymbolA();

        if (!dataAccessObject.existsByCode(symbolB)) {
            convertPresenter.prepareFailView(symbolB + ": Currency Code does not exist (B).");
        } else if (!currencyB.matches("\\d+")) { //소수점 되게 regex modify
            convertPresenter.prepareFailView(currencyB + ": Currency has non-numerical values in it.");
        } else if (!dataAccessObject.existsByCode(symbolA)) {
            convertPresenter.prepareFailView(symbolA + ": Currency Code does not exist (A).");
        } else {
            if (symbolB.equals(symbolA)) {
                convertPresenter.prepareFailView("Exchange does not happen for same currency codes.");
            } else {
                String[] currency = dataAccessObject.get(symbolA).split(":");
                ConvertOutputData convertOutputData = new ConvertOutputData(currency[0], currency[1], false);
                convertPresenter.prepareSuccessView(convertOutputData);
            }
        }

    }
}
