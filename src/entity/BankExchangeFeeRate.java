package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;

public class BankExchangeFeeRate {

    static double defaultFeeRate = 0.0;
    static Map<String, Double> bankRates = Map.of("RBC", 0.01, "BMO", 0.025, "Scotia", 0.02,
            "TD", 0.03, "CIBC", 0.008);

    public static double performExchangeFee(String bank) {

        return bankRates.getOrDefault(bank, defaultFeeRate);
    }
}
