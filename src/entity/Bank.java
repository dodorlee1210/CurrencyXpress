package entity;
public class Bank {

    private String name;
    private double exchangeFeeRate;

    public Bank() {}

    public Bank(String name){
        this.name = name;
        this.exchangeFeeRate = BankExchangeFeeRate.performExchangeFee(this.name);


    }

    public String getName(){
        return name;
    }
    public double getExchangeFeeRate() {
        return exchangeFeeRate;
    }

}
