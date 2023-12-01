package entity.banks;

import java.util.HashMap;

public class BMO implements Bank {
    public final String bankName = "BMO";
    private final double exchangeServiceFee = 0.025;

    @Override
    public double getExchangeServiceFee() {
        return exchangeServiceFee;
    }

}
