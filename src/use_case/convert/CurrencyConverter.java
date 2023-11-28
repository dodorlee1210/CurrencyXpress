package use_case.convert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyConverter implements ConvertDataAccessInterface {

    String[] currenciesSplit;
    String currencies;

    public CurrencyConverter(String[] args) {
        String apiKey = "691b8b16ea3f2b197ffc3beba516d080"; // Replace with your API key

        try {
            // Set the URL with the API endpoint and access key
            String url = "http://api.exchangeratesapi.io/v1/latest?access_key=" + apiKey;

            // Create a URL object
            URL apiUrl = new URL(url);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
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


//            for (String s : output) {
//                System.out.println(s);
//            }

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
