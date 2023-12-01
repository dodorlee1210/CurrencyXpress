package entity.banks;

import entity.Account;

import java.util.HashMap;

public class RBC implements Bank {
    public final String bankName = "RBC";
    private final double exchangeServiceFee = 0.001;
    private final HashMap<String, Account> accounts = new HashMap<>();

    @Override
    public double getExchangeServiceFee() {
        return exchangeServiceFee;
    }

}
