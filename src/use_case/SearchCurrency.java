package use_case;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SearchCurrency {
    public static void main(String[] args) {
        String apiKey = "691b8b16ea3f2b197ffc3beba516d080";


        try {
            String apiUrl = "http://api.exchangeratesapi.io/v1/";

            // Assume that base and symbols are retrieved from the front end
            String date = "2023-01-01"; // Replace with the actual date
            String baseCurrency = "GBP"; // Replace with the actual base currency
            String symbols = "USD,CAD,EUR"; // Replace with the actual symbols

            // Build the URL with retrieved parameters
            String url = apiUrl + date + "/latest?access_key=" + apiKey;

            // Append retrieved values for base and symbols if available
            if (!symbols.isEmpty() && !baseCurrency.isEmpty()) {
                url += "&base=" + baseCurrency +  "&symbols=" + symbols;
            }

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
            System.out.println("Exchange Rates for " + date + ":");
            System.out.println(response.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}

