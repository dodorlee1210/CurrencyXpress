package use_case.convert;

public class ConvertInputData {
    private String symbolB;
    private String currencyB;
    private String symbolA;
    private String username;

    public ConvertInputData(String symbolB, String currencyB, String symbolA, String username) {
        this.symbolB = symbolB;
        this.currencyB = currencyB;
        this.symbolA = symbolA;
        this.username = username;
    }

    public String getSymbolB() {
        return symbolB;
    }

    public String getCurrencyB() {
        return currencyB;
    }

    public String getSymbolA() {
        return symbolA;
    }

    public String getUsername() {
        return username;
    }
}
