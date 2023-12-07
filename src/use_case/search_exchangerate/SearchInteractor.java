package use_case.search_exchangerate;

import data_access.FileUserDataAccessObject;
import use_case.SearchCurrency;
import use_case.convert.ConvertOutputData;
import use_case.convert.CurrencyConverter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SearchInteractor implements SearchInputBoundary {

    SearchDataAccessInterface searchDataAccessObject;

    final SearchOutputBoundary searchPresenter;

    FileUserDataAccessObject dataAccessObject;
    String exchangeResult;



    public SearchInteractor(SearchOutputBoundary searchOutputBoundary,
                            FileUserDataAccessObject dataAccessObject) {


        this.searchPresenter = searchOutputBoundary;
        this.dataAccessObject = dataAccessObject;
    }
    public boolean checkDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false); // Make sure the date is strictly validated

        try {
            // Parse the input date
            Date parsedDate = dateFormat.parse(date);

            // Check if the parsed date is within the allowed range
            Date lowerBound = dateFormat.parse("1999-01-01");
            Date upperBound = new Date(); // Current date

            return !parsedDate.before(lowerBound) && !parsedDate.after(upperBound);
        } catch (ParseException e) {
            // The input date is not in the expected format
            return false;
        }
    }



    public String execute(SearchInputData searchInputData) {
        String date = searchInputData.getDate();
        String baseCurrency = searchInputData.getBaseCurrency();
        String symbols = searchInputData.getSymbols();
//        try {
//            searchDataAccessObject = new SearchCurrency(searchInputData);
//        } catch (
//                IOException e) {
//            throw new RuntimeException(e);
//        }


        if (symbols.equals("HOME")) {
            searchPresenter.prepareSuccessView(new SearchOutputData("HOME",
                    "", false));
        } else if (!checkDate(searchInputData.getDate())) {
            System.out.println(date);
            searchPresenter.prepareFailView(date + ": This date is not a valid date.");
            exchangeResult = "Error:" + " This date is not a valid date.";

//        }   else if (!searchDataAccessObject.existsByCode(baseCurrency)) {
//            searchPresenter.prepareFailView(baseCurrency + ": Currency Code is not valid as a base currency.");
//            exchangeResult = "Error:" + " Currency code is not valid as a base currency.";
        } else {
            if (baseCurrency.equals(symbols)){
                searchPresenter.prepareFailView("Exchange does not happen for same currency codes.");
                exchangeResult = "Error:" + " Exchange does not happen for same currency codes.";
            } else {

                try {
                    searchDataAccessObject = new SearchCurrency(searchInputData);
                } catch (
                        IOException e) {
                    throw new RuntimeException(e);
                }
                String[] currency = searchDataAccessObject.get(symbols).split(":");
                SearchOutputData searchOutputData = new SearchOutputData(currency[0],currency[1],false);
                searchPresenter.prepareSuccessView(searchOutputData);

                exchangeResult = symbols + "\n" + searchOutputData.get_afterCurrency();
            }

        }
        return exchangeResult;
    }
}


