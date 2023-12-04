package use_case.search_exchangerate;

public class SearchInputData {

    private String date;
    private String baseCurrency;
    private String symbols;

    public SearchInputData(String date, String baseCurrency, String symbols) {
        this.date = "to be input by user";
        this.baseCurrency = "to be input by user";
        this.symbols = "to be input by user";
    }
    String getDate() {
        return date;
    }

    String getBaseCurrency() {
        return baseCurrency;
    }

    String getSymbols() {
        return symbols;
    }

}
