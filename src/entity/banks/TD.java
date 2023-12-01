package entity.banks;

import entity.Account;

import java.util.HashMap;

public class TD implements Bank {
    public final String bankName = "TD";
    private final double exchangeServiceFee = 0.03;
    private final HashMap<String, Account> accounts = new HashMap<>();

    @Override
    public double getExchangeServiceFee() {
        return exchangeServiceFee;
    }
}
