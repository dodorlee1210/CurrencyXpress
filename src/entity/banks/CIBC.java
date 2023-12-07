package entity.banks;

import entity.Account;

import java.util.HashMap;

public class CIBC implements Bank {
    public final String bankName = "CIBC";
    private final double exchangeServiceFee = 0.018;
    private final HashMap<String, Account> accounts = new HashMap<>();

    /**
     * The CIBC bank's exchange service fee charge (%) represented as double value
     * @return CIBC's exchange service fee
     */
    @Override
    public double getExchangeServiceFee() {
        return exchangeServiceFee;
    }

    /**
     * Get the bank name
     * @return the bank name: CIBC
     */
    @Override
    public String getBankName() {
        return bankName;
    }
}
