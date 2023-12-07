package use_case.convert;

public class ConvertOutputData {

    private String symbolA;
    private String currencyA;
    private double leftAmount;
    private boolean useCaseFailed;
    private String[][] currencies;

    public ConvertOutputData(String symbolA, String currencyA, double leftAmount,
                             String[][] currencies, boolean useCaseFailed) {
        this.symbolA = symbolA;
        this.currencyA = currencyA;
        this.leftAmount = leftAmount;
        this.currencies = currencies;
        this.useCaseFailed = useCaseFailed;
    }

    public String getSymbolA() { return symbolA; }

    public String getCurrencyA() { return currencyA; }

    public double getLeftAmount() {
        return leftAmount;
    }

    public String[][] getCurrencies() {
        return currencies;
    }
    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
