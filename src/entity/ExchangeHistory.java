package entity;

import java.time.LocalDateTime;

public class ExchangeHistory {
    private String sourceCurrency;
    private String targetCurrency;
    private double exchangedAmount;
    private double exchangeRate;
    private LocalDateTime exchangeTime;

    // Constructor
    public ExchangeHistory(String sourceCurrency, String targetCurrency, double exchangedAmount, double exchangeRate) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.exchangedAmount = exchangedAmount;
        this.exchangeRate = exchangeRate;
        this.exchangeTime = LocalDateTime.now();
    }

    // Getters
    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public double getExchangedAmount() {
        return exchangedAmount;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public LocalDateTime getExchangeTime() {
        return exchangeTime;
    }
    // Exchange
    public String toString() {
        return String.format("Source Currency: %s, Target Currency: %s, Exchanged Amount: %.2f, Exchange Rate: %.4f, Exchange Time: %s",
                sourceCurrency, targetCurrency, exchangedAmount, exchangeRate, exchangeTime);
    }
}

