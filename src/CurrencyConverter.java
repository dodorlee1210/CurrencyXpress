import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyConverter {
    public static void main(String[] args) {
        String apiKey = "691b8b16ea3f2b197ffc3beba516d080";
        String baseCurrency = "USD"; // The currency you want to exchange from
        String targetCurrency = "EUR"; // The currency you want to exchange to
        double amountToConvert = 100.0; // The amount you want to convert

        try {
            URL url = new URL("https://api.apilayer.com/exchangerates_data/latest?base=" + baseCurrency + "&symbols=" + targetCurrency);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
                connection.disconnect();

                // Parse the JSON response to get the exchange rate
                String jsonResponse = response.toString();
                double exchangeRate = Double.parseDouble(jsonResponse.split(":")[1].replaceAll("[^\\d.]", ""));
                double convertedAmount = amountToConvert * exchangeRate;

                System.out.println(amountToConvert + " " + baseCurrency + " is equivalent to " + convertedAmount + " " + targetCurrency);
            } else {
                System.err.println("Failed to retrieve exchange rate. Response code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
