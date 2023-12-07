package use_case.search_exchangerate;

public class SearchInputData {

    private String date;
    private String baseCurrency;
    private String symbols;

    public SearchInputData(String date, String baseCurrency, String symbols) {
        this.date = date;
        this.baseCurrency = baseCurrency;
        this.symbols = symbols;
    }
    public String getDate() {
        return date;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getSymbols() {
        return symbols;
    }

}
