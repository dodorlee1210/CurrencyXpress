package use_case.convert;

public class ConvertInputData {
    private String symbolB;
    private String currencyB;
    private String symbolA;

    public ConvertInputData(String symbolB, String currencyB, String symbolA) {
        this.symbolB = "to be selected by user";
        this.currencyB = "to be input by user";
        this.symbolA = "to be selected by user";
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
