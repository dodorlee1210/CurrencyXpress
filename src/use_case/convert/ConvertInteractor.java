package use_case.convert;
import entity.banks.Bank;

public class ConvertInteractor implements ConvertInputBoundary {

    final ConvertDataAccessInterface dataAccessObject;
    final ConvertOutputBoundary convertPresenter;
    final Bank bank;

    public ConvertInteractor(ConvertDataAccessInterface dataAccessInterface, ConvertOutputBoundary convertOutputBoundary, Bank bank) {
        this.dataAccessObject = dataAccessInterface;
        this.convertPresenter = convertOutputBoundary;
        this.bank = bank;
    }

    @Override
    public void execute(ConvertInputData convertInputData) {
        String symbolB = convertInputData.getSymbolB();
        String currencyB = convertInputData.getCurrencyB();
        String symbolA = convertInputData.getSymbolA();

        if (!dataAccessObject.existsByCode(symbolB)) {
            convertPresenter.prepareFailView(symbolB + ": Currency Code does not exist (B).");
        } else if (!currencyB.matches("\\d+(\\.\\d+)?")) {
            convertPresenter.prepareFailView(currencyB + ": Currency has non-numerical values in it.");
        } else if (!dataAccessObject.existsByCode(symbolA)) {
            convertPresenter.prepareFailView(symbolA + ": Currency Code does not exist (A).");
        } else {
            if (symbolB.equals(symbolA)) {
                convertPresenter.prepareFailView("Exchange does not happen for same currency codes.");
            } else {
                String[] currency = dataAccessObject.get(symbolA).split(":");
                String exchangedAmount = dataAccessObject.calculateExchange(currencyB, currency[1], bank.getExchangeServiceFee());
                ConvertOutputData convertOutputData = new ConvertOutputData(currency[0], exchangedAmount, false);
                convertPresenter.prepareSuccessView(convertOutputData);
            }
        }

    }
}
