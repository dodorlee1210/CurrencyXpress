package entity.banks;

import entity.Account;

import java.util.HashMap;

public class RBC implements Bank {
    public final String bankName = "RBC";
    private final double exchangeServiceFee = 0.001;
    private final HashMap<String, Account> accounts = new HashMap<>();

    /**
     * The RBC bank's exchange service fee charge (%) represented as double value
     * @return RBC's exchange service fee
     */
    @Override
    public double getExchangeServiceFee() {
        return exchangeServiceFee;
    }

    /**
     * Get the bank name
     * @return the bank name: RBC
     */
    @Override
    public String getBankName() {
        return bankName;
    }
}
