package use_case;
import use_case.search.SearchDataAccessInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SearchCurrency implements SearchDataAccessInterface {
    String[] currenciesSplit;
    String currencies;
    public SearchCurrency() throws IOException {
        String apiKey = "691b8b16ea3f2b197ffc3beba516d080";


        try {
            String apiUrl = "http://api.exchangeratesapi.io/v1/";

            // Assume that base and symbols are retrieved from the front end
            String date = "2023-01-01"; // Replace with the actual date
            String baseCurrency = "EUR"; // Replace with the actual base currency
            String symbols = "USD"; // Replace with the actual symbols

            // Build the URL with retrieved parameters
            String url = apiUrl + date + "?access_key=" + apiKey;

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

