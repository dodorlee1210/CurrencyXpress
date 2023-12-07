package entity.banks;

public interface Bank {
    /**
     * The bank has its own unique exchange service fee (esf) and this method will return the esf.
     * @return the bank's exchange service fee
     */
    double getExchangeServiceFee();

    /**
     * The bank has its own unique name and this method will return the bank's name
     * @return bank name
     */
    String getBankName();
}
