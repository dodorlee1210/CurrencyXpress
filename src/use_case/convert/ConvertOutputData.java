package use_case.convert;

public class ConvertOutputData {

    private String symbolA;
    private String currencyA;
    private boolean useCaseFailed;

    public ConvertOutputData(String symbolA, String currencyA, boolean useCaseFailed) {
        this.symbolA = symbolA;
        this.currencyA = currencyA;
        this.useCaseFailed = useCaseFailed;
    }

    public String getSymbolA() { return symbolA; }

    public String getCurrencyA() { return currencyA; }

}
