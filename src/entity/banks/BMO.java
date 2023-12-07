package entity.banks;

import java.util.HashMap;

public class BMO implements Bank {
    public final String bankName = "BMO";
    private final double exchangeServiceFee = 0.025;

    /**
     * The BMO bank's exchange service fee charge (%) represented as double value
     * @return BMO's exchange service fee
     */
    @Override
    public double getExchangeServiceFee() {
        return exchangeServiceFee;
    }

    /**
     * Get the bank name
     * @return the bank name: BMO
     */
    @Override
    public String getBankName() {
        return bankName;
    }
}
