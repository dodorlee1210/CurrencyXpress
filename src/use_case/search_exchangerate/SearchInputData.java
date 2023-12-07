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

    /**
     * The date is required for the API usage
     * @return the date for the currency rate search
     */
    public String getDate() {
        return date;
    }

    /**
     * @return the currency rate for the exchange
     */
    public String getBaseCurrency() {
        return baseCurrency;
    }

    /**
     * @return the symbol which represents the currency code
     */
    public String getSymbols() {
        return symbols;
    }

}
