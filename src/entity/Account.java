package entity;

import entity.banks.Bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Account {
    private String accountHolder;
    private double balance;
    private final Bank bank;
    private final List<ExchangeHistory> exchangeHistories = new ArrayList<>();
    private final HashMap<String, Double> foreignCurrency  = new HashMap<>();


    public Account(String accountHolder, Bank bank, double balance) {
        this.accountHolder = accountHolder;
        this.bank = bank;
        this.balance = balance;
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

    // Add an exchange to the history
    public void addExchangeHistory(ExchangeHistory history) {
        exchangeHistories.add(history);
    }

    // Get the exchange history
    public List<ExchangeHistory> getExchangeHistory() {
        return exchangeHistories;
    }

    public String getBankName() {
        return bank.getBankName();
    }

    public Bank getBank() {
        return bank;
    }

    public void setForeignCurrency(String currencyCode, double amount) {
        if (foreignCurrency.containsKey(currencyCode)) {
            foreignCurrency.replace(currencyCode, foreignCurrency.get(currencyCode) + amount);
        }

        else {
            foreignCurrency.put(currencyCode, amount);
        }
    }

    public double getForeignCurrency(String currencyCode) {
        double balance = 0.0;

        if (foreignCurrency.containsKey(currencyCode)) {
            balance = foreignCurrency.get(currencyCode);
        }

        return balance;
    }

    /**
     * @return the list of all foreign currencies saved in this account in the format of
     *         [ ["currency code", "corresponding balance"], ... ]
     */
    public String[][] getAllForeignCurrencies() {
        int i = 0;
        String[][] foreignCurrencies = new String[foreignCurrency.size()][];

        for (String currencyCode: foreignCurrency.keySet()) {
            String[] singleForeignCurrency = {currencyCode, String.valueOf(foreignCurrency.get(currencyCode))};
            foreignCurrencies[i++] = singleForeignCurrency;
        }

        return foreignCurrencies;
    }

    /**
     * Return whether the account has at least 1 foreign currency
     * @return true if the account has a foreign currency. Otherwise, false.
     */
    public boolean hasForeignCurrency() {
        return !foreignCurrency.isEmpty();
    }

    /**
     * Return whether the account has a specified foreign currency
     * @return true if the account has a specified foreign currency. Otherwise, false.
     */
    public boolean hasForeignCurrency(String currencyCode) {
        return foreignCurrency.containsKey(currencyCode);
    }
}

