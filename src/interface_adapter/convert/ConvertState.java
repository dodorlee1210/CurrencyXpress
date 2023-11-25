package interface_adapter.convert;

import interface_adapter.login.LoginState;

public class ConvertState {
    private String symbolB = "";
    private String symbolBError = null;
    private String currency = "";
    private String currencyError = null;
    private String symbolA = "";
    private String symbolAError = null;

    public ConvertState(ConvertState copy) {
        symbolB = copy.symbolB;
        symbolBError = copy.symbolBError;
        currency = copy.currency;
        currencyError = copy.currencyError;
        symbolA = copy.symbolA;
        symbolAError = copy.symbolAError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public ConvertState() {}

    public String getSymbolB() {
        return symbolB;
    }

    public String getSymbolBError() {
        return symbolBError;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCurrencyError() {
        return currencyError;
    }

    public String getSymbolA() {
        return symbolA;
    }

    public String getSymbolAError() {
        return symbolAError;
    }

    public void setSymbolB(String symbolB) {
        this.symbolB = symbolB;
    }

    public void setSymbolBError(String symbolBError) {
        this.symbolBError = symbolBError;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setCurrencyError(String currencyError) {
        this.currencyError = currencyError;
    }

    public void setSymbolA(String symbolA) {
        this.symbolA = symbolA;
    }

    public void setSymbolAError(String symbolAError) {
        this.symbolAError = symbolAError;
    }
}
