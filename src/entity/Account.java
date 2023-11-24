package entity;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountHolder;
    private double balance;
    private List<ExchangeHistory> exchangeHistories;

    // Constructor
    public Account(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.exchangeHistories = new ArrayList<>();
    }

    // Getters and setters
    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Method to add an exchange to the history
    public void addExchangeHistory(String sourceCurrency, String targetCurrency, double exchangedAmount, double exchangeRate) {
        ExchangeHistory history = new ExchangeHistory(sourceCurrency, targetCurrency, exchangedAmount, exchangeRate);
        exchangeHistories.add(history);
    }

    // Method to get the exchange history
    public List<ExchangeHistory> getExchangeHistory() {
        return exchangeHistories;
    }
}
