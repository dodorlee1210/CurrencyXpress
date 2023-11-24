package use_case.convert;

public class ConvertInputData {
    private String symbolB;
    private String currencyB;

    public ConvertInputData(String symbolB, String currencyB) {
        this.symbolB = "to be pulled from api 1st endpoint";
        this.currencyB = "to be pulled from api 1st endpoint";
    }

    String getSymbolB() {
        return symbolB;
    }

    String getCurrencyB() {
        return currencyB;
    }
}
