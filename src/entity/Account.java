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
    /**
     * Get the account's owner's name
     * @return the account's owner's name
     */
    public String getAccountHolder() {
        return accountHolder;
    }

    /**
     * Update this account's owner's name
     * @param accountHolder new account owner's name
     */
    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    /**
     * @return  the balance of this account
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance   update this account's balance to the given balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Add exchange history for this account
     * @param history   New exchange history for this account
     */
    public void addExchangeHistory(ExchangeHistory history) {
        exchangeHistories.add(history);
    }

    /**
     * @return  All the exchange histories made by this account as a list
     */
    public List<ExchangeHistory> getExchangeHistory() {
        return exchangeHistories;
    }

    /**
     * @return  The bank's name that owns this account
     */
    public String getBankName() {
        return bank.getBankName();
    }

    /**
     * @return  The bank object that owns this account
     */
    public Bank getBank() {
        return bank;
    }

    /**
     * Update the foreign currency in this account
     *
     * @param currencyCode  currency code that represents the foreign currency
     * @param amount    the balance of specified foreign currency
     */
    public void setForeignCurrency(String currencyCode, double amount) {
        if (foreignCurrency.containsKey(currencyCode)) {
            foreignCurrency.replace(currencyCode, foreignCurrency.get(currencyCode) + amount);
        }

        else {
            foreignCurrency.put(currencyCode, amount);
        }
    }

    /**
     * Return specified foreign currency's balance in this account
     *
     * @param currencyCode  Currency code that represents the foreign currency
     * @return  Corresponding foreign currency balance to the given currency code
     */
    public double getForeignCurrency(String currencyCode) {
        double balance = 0.0;

        if (foreignCurrency.containsKey(currencyCode)) {
            balance = foreignCurrency.get(currencyCode);
        }

        return balance;
    }

    /**
     * @return the array list of all foreign currencies saved in this account in the format of
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


