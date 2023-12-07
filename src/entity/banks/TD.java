package entity.banks;

import entity.Account;

import java.util.HashMap;

public class TD implements Bank {
    public final String bankName = "TD";
    private final double exchangeServiceFee = 0.03;
    private final HashMap<String, Account> accounts = new HashMap<>();

    /**
     * The TD bank's exchange service fee charge (%) represented as double value
     * @return TD's exchange service fee
     */
    @Override
    public double getExchangeServiceFee() {
        return exchangeServiceFee;
    }

    /**
     * Get the bank name
     * @return the bank name: TD
     */
    @Override
    public String getBankName() {
        return bankName;
    }
}
