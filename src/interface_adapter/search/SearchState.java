package interface_adapter.search;

public class SearchState{

    private String date = "";
    private String dateError = null;
    private String baseCurrency = "";
    private String baseCurrencyError = null;
    private String symbols = "";
    private String symbolsError = null;

    private String afterSymbols = "";
    private String afterSymbolsError = null;
    private String afterCurrency = "";
    private String afterCurrencyError = null;

    public SearchState(SearchState copy){
        date = copy.date;
        dateError = copy.dateError;
        baseCurrency = copy.baseCurrency;
        baseCurrencyError = copy.baseCurrencyError;
        symbols = copy.symbols;
        symbolsError = copy.symbolsError;
        afterSymbols = copy.afterSymbols;
        afterSymbolsError = copy.afterSymbolsError;
        afterCurrency = copy.afterCurrency;
        afterCurrencyError = copy.afterCurrencyError;
    }
    public SearchState() {}
    public String getDate() {return date;}
    public String getDateError() {return dateError;}
    public String getBaseCurrency() {return baseCurrency;}
    public String getBaseCurrencyError() {return baseCurrencyError;}
    public String getSymbols() {return symbols;}
    public String getSymbolsError() {return symbolsError;}
    public String getAfterSymbols() {return afterSymbols;}
    public String getAfterSymbolsError() {return afterSymbolsError;}
    public String getAfterCurrency() {return afterCurrency;}
    public String getAfterCurrencyError() {return afterCurrencyError;}

    public void setDate(String date) {this.date = date;}
    public void setDateError(String dateError) {this.dateError = dateError;}
    public void setBaseCurrency(String baseCurrency) {this.baseCurrency = baseCurrency;}
    public void setBaseCurrencyError(String baseCurrencyError) {this.baseCurrencyError = baseCurrencyError;}
    public void setSymbols(String symbols) {this.symbols = symbols;}
    public void setSymbolsError(String symbolsError) {this.symbolsError = symbolsError;}
    public void setAfterSymbols(String afterSymbols) {this.afterSymbols = afterSymbols;}
    public void setAfterSymbolsError(String afterSymbolsError) {this.afterSymbolsError = afterSymbolsError;}
    public void setAfterCurrency(String afterCurrency) {this.afterCurrency = afterCurrency;}
    public void setAfterCurrencyError(String afterCurrencyError) {this.afterCurrencyError = afterCurrencyError;}
}