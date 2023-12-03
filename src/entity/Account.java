package entity;

import entity.banks.*;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountHolder;
    private double balance;
    private Bank bank;
    private List<ExchangeHistory> exchangeHistories;

    // Account Constructor
    public Account(String accountHolder, String bankName) {
        this(accountHolder, bankName, 0.0);
    }

    public Account(String accountHolder, String bankName, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.bank = getBank(bankName);
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

    /**
     * Return the bank object corresponding to the bank name
     * @param bankName The object type to return
     * @return Bank object corresponding to the bank name
     */
    private Bank getBank(String bankName) {
        return switch (bankName) {
            case "BMO" -> new BMO();
            case "CIBC" -> new CIBC();
            case "RBC" -> new RBC();
            case "Scotia" -> new Scotia();
            default -> new TD();
        };
    }

    public String getBankName() {
        return bank.getBankName();
    }

    public double getExchangeServiceFee() {
        return bank.getExchangeServiceFee();
    }
}
