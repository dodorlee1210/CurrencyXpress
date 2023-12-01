package entity.banks;

import entity.Account;

import java.util.HashMap;

public class CIBC implements Bank {
    public final String bankName = "CIBC";
    private final double exchangeServiceFee = 0.018;
    private final HashMap<String, Account> accounts = new HashMap<>();

    @Override
    public double getExchangeServiceFee() {
        return exchangeServiceFee;
    }

}
