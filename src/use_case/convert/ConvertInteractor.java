package use_case.convert;

import data_access.FileUserDataAccessObject;
import entity.Account;
import entity.ExchangeHistory;
import entity.User;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConvertInteractor implements ConvertInputBoundary {

    final ConvertDataAccessInterface convertDataAccessInterface;
    final ConvertOutputBoundary convertPresenter;
    FileUserDataAccessObject dataAccessObject;
    String exchangeResult;


    public ConvertInteractor(ConvertDataAccessInterface convertDataAccessInterface,
                             ConvertOutputBoundary convertOutputBoundary, FileUserDataAccessObject dataAccessObject) {
        this.dataAccessObject = dataAccessObject;
        this.convertPresenter = convertOutputBoundary;
        this.convertDataAccessInterface = convertDataAccessInterface;
    }

    @Override
    public String execute(ConvertInputData convertInputData) {
        String symbolB = convertInputData.getSymbolB();
        String currencyB = convertInputData.getCurrencyB();
        String symbolA = convertInputData.getSymbolA();
        User user = dataAccessObject.get(convertInputData.getUsername());
        Account account = user.getUserAccount();

        if (symbolA.equals("HOME")) {
            convertPresenter.prepareSuccessView(new ConvertOutputData("HOME",
                    "", account.getBalance(), false));
        } else if (!convertDataAccessInterface.existsByCode(symbolB)) {
            convertPresenter.prepareFailView(symbolB + ": Currency Code does not exist (B).");
            exchangeResult = "Error: " + "Currency Code does not exist (B).";
        } else if (!currencyB.matches("\\d+(\\.\\d+)?")) {
            convertPresenter.prepareFailView(currencyB + ": Currency has non-numerical values in it.");
            exchangeResult = "Error: " + "Currency has non-numerical values in it.";
        } else if (!convertDataAccessInterface.existsByCode(symbolA)) {
            convertPresenter.prepareFailView(symbolA + ": Currency Code does not exist (A).");
            exchangeResult = "Error: " + "Currency Code does not exist (A).";
        } else {
            if (symbolB.equals(symbolA)) {
                convertPresenter.prepareFailView("Exchange does not happen for same currency codes.");
                exchangeResult = "Error: " + "Exchange does not happen for same currency codes.";
            } else {
                String[] currency = convertDataAccessInterface.get(symbolA).split(":");
                double serviceFees = account.getBank().getExchangeServiceFee();
                String exchangedAmount = convertDataAccessInterface.calculateExchange(currencyB, currency[1], serviceFees);
                double leftover = account.getBalance() - Double.parseDouble(currencyB);

                ConvertOutputData convertOutputData = new ConvertOutputData(currency[0], exchangedAmount,
                        leftover,false);
                convertPresenter.prepareSuccessView(convertOutputData);

                double newAccountBalance = convertOutputData.getLeftAmount();
                // round down the double value up to 2 decimal place
                //   ex: 1.246   = 1.24
                //   ex: 3.21194 = 3.21
                double exchangedValue = BigDecimal.valueOf(Double.parseDouble(exchangedAmount)).setScale(2, RoundingMode.HALF_DOWN).doubleValue();

                exchangeResult = "Convert " + symbolB + " to " + symbolA + "\n" +
                        exchangedValue + "\n" + "Balance: " + newAccountBalance;

                updateUserAccount(account, newAccountBalance, symbolA, exchangedValue);
                addExchangeHistory(account, symbolB, symbolA, exchangedValue, serviceFees);
            }
        }

        return exchangeResult;
    }

    private void updateUserAccount(Account userAccount, double newAccountBalance,
                                   String newCurrencyCode, double newCurrencyBalance) {
        userAccount.setBalance(newAccountBalance);
        userAccount.setForeignCurrency(newCurrencyCode, newCurrencyBalance);
    }

    private void addExchangeHistory(Account userAccount, String currencyCode,
                                    String newCurrencyCode, double exchangedValue, double serviceFee) {
        userAccount.addExchangeHistory(new ExchangeHistory(currencyCode, newCurrencyCode, exchangedValue, serviceFee));
    }
}
