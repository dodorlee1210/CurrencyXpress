package use_case.convert;

public class ConvertInputData {
    private String symbolB;
    private String currencyB;
    private String symbolA;

    public ConvertInputData(String symbolB, String currencyB, String symbolA) {
        this.symbolB = symbolB;
        this.currencyB = currencyB;
        this.symbolA = symbolA;
    }

    String getSymbolB() {
        return symbolB;
    }

    String getCurrencyB() {
        return currencyB;
    }

    String getSymbolA() {
        return symbolA;
    }

}
