package entity;

import entity.banks.Bank;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountHolder;
    private double balance;
    private Bank bank;
    private List<ExchangeHistory> exchangeHistories;

    // Account Constructor

    public Account() {
        this("", null, 0.0);
    }

    public Account(String accountHolder, Bank bank, double balance) {
        this.accountHolder = accountHolder;
        this.bank = bank;
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
