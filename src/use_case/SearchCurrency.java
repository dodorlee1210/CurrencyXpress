package use_case;
import use_case.search_exchangerate.SearchDataAccessInterface;
import use_case.search_exchangerate.SearchInputData;
import use_case.search_exchangerate.SearchInteractor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SearchCurrency implements SearchDataAccessInterface{
    String[] currenciesSplit;
    String currencies;
    public SearchCurrency(SearchInputData searchInputData) throws IOException {
        String apiKey = "691b8b16ea3f2b197ffc3beba516d080";

        String apiUrl = "http://api.exchangeratesapi.io/v1/";
        try {

            // Assume that base and symbols are retrieved from the front end
            String date = searchInputData.getDate();// Replace with the actual date

            String baseCurrency = searchInputData.getBaseCurrency(); // Replace with the actual base currency
            String symbols = searchInputData.getSymbols(); // Replace with the actual symbols

            // Build the URL with retrieved parameters
            String url = apiUrl + date + "?access_key=" + apiKey;
            System.out.println(url);
            URL exchangeRateUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) exchangeRateUrl.openConnection();
            connection.setRequestMethod("GET");

            // Get the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            // Print the response
            String[] output;
            output = response.toString().split(",");
            this.currenciesSplit = output;
//            System.out.println(response);
            this.currencies = response.toString();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean existsByCode(String identifier) {
        return currencies.contains(identifier);
    }


    @Override
    public String get(String code) {
        for (String s : currenciesSplit) {
            if (s.contains(code)) {
                return s;
            }
        }
        return code;
    }


    }



