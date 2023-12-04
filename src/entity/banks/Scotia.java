package entity.banks;

import entity.Account;

import java.util.HashMap;

public class Scotia implements Bank {
    public final String bankName = "Scotia";
    private final double exchangeServiceFee = 0.02;
    private final HashMap<String, Account> accounts = new HashMap<>();

    @Override
    public double getExchangeServiceFee() {
        return exchangeServiceFee;
    }

    @Override
    public String getBankName() {
        return bankName;
    }
}
