package use_case.search_exchangerate;

public class SearchOutputData {

    private String afterSymbols;
    private String afterCurrency;
    private boolean useCaseFailed;

    public SearchOutputData(String afterSymbols, String afterCurrency, boolean useCaseFailed){
        this.afterSymbols = afterSymbols;
        this.afterCurrency = afterCurrency;
        this.useCaseFailed = useCaseFailed;
    }
    public String get_afterSymbols() {return afterSymbols;}

    public String get_afterCurrency() {return afterCurrency;}
    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
