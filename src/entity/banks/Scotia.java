package entity.banks;

import entity.Account;

import java.util.HashMap;

public class Scotia implements Bank {
    public final String bankName = "Scotia";
    private final double exchangeServiceFee = 0.02;
    private final HashMap<String, Account> accounts = new HashMap<>();

    /**
     * The Scotia bank's exchange service fee charge (%) represented as double value
     * @return Scotia's exchange service fee
     */
    @Override
    public double getExchangeServiceFee() {
        return exchangeServiceFee;
    }

    /**
     * Get the bank name
     * @return the bank name: Scotia
     */
    @Override
    public String getBankName() {
        return bankName;
    }
}
