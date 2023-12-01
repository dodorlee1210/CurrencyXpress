package entity;

import entity.ExchangeHistory;
import entity.banks.Bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Account {
    private String accountHolder;
    private double balance;
    private Bank bank;
    private List<ExchangeHistory> exchangeHistories;
    private final HashMap<String, Double> foreignCurrency = new HashMap<>();

    // Account Constructor
    public Account(String accountHolder, Bank bank) {
        this(accountHolder, bank, 0.0);
    }

    public Account(String accountHolder, Bank bank, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.bank = bank;
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

    public void deposit(int balance) {
        this.balance += balance;
    }

    public void setForeignCurrency(String currencyType, double balance) {
        if (foreignCurrency.containsKey(currencyType)) {
            foreignCurrency.replace(currencyType, foreignCurrency.get(currencyType) + balance);
        }

        else {
            foreignCurrency.put(currencyType, balance);
        }
    }

    public Double getForeignCurrencyBalance(String currencyType) {
        return foreignCurrency.get(currencyType);
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
}
