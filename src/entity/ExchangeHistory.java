package entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class ExchangeHistory {
    private final String sourceCurrency;
    private final String targetCurrency;
    private double exchangedAmount;
    private final double exchangeRate;
    private final LocalDateTime exchangeTime;

    // Constructor
    public ExchangeHistory(String sourceCurrency, String targetCurrency, double exchangedAmount, double exchangeRate) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.exchangedAmount = exchangedAmount;
        this.exchangeRate = exchangeRate;
        this.exchangeTime = LocalDateTime.now();
    }

    // Getters
    /**
     * @return the currency code that is being exchanged
     */
    public String getSourceCurrency() {
        return sourceCurrency;
    }

    /**
     * @return the currency code that is being received from the exchange service
     */
    public String getTargetCurrency() {
        return targetCurrency;
    }

    /**
     * @return the amount (result) of exchanged currency
     */
    public double getExchangedAmount() {
        return exchangedAmount;
    }

    /**
     * @return the exchange rate (depends on the currency codes, ex: 1 eur = 1.47 cad)
     */
    public double getExchangeRate() {
        return exchangeRate;
    }

    /**
     * @return return the date and time of exchanged occurred
     */
    public LocalDateTime getExchangeTime() {
        return exchangeTime;
    }

    /**
     * @return string representation of this object
     */
    public String toString() {
        exchangedAmount = BigDecimal.valueOf(Double.parseDouble(String.valueOf(exchangedAmount))).setScale(2, RoundingMode.HALF_DOWN).doubleValue();

        return String.format("Source Currency: %s, Target Currency: %s, Exchanged Amount: %f, Exchange Rate: %.4f, Exchange Time: %s",
                sourceCurrency, targetCurrency, exchangedAmount, exchangeRate, exchangeTime);
    }
}

