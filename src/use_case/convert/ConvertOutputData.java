package use_case.convert;

public class ConvertOutputData {

    private String symbolA;
    private String currencyA;
    private double leftAmount;
    private boolean useCaseFailed;

    public ConvertOutputData(String symbolA, String currencyA, double leftAmount, boolean useCaseFailed) {
        this.symbolA = symbolA;
        this.currencyA = currencyA;
        this.leftAmount = leftAmount;
        this.useCaseFailed = useCaseFailed;
    }

    public String getSymbolA() { return symbolA; }

    public String getCurrencyA() { return currencyA; }

    public double getLeftAmount() {
        return leftAmount;
    }
}
