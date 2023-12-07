
import entity.*;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import entity.banks.*;
import use_case.SearchCurrency;
import use_case.account.AccountInputData;
import use_case.account.AccountOutputData;
import use_case.convert.ConvertInputData;
import use_case.convert.ConvertOutputData;
import use_case.convert.CurrencyConverter;
import use_case.login.LoginInputData;
import use_case.login.LoginOutputData;
import use_case.search_exchangerate.SearchInputData;
import use_case.search_exchangerate.SearchOutputData;
import use_case.signup.SignupInputData;
import use_case.signup.SignupOutputData;
import use_case.view_exchangehistory.ViewExchangeHistoryInputData;
import use_case.view_exchangehistory.ViewExchangeHistoryOutputData;

public class UsecaseTests {
    @Test
    public void testAccountInputDataGetUsername() {
        String username = "john_doe";
        String method = "transfer";
        String bank = "XYZ Bank";

        AccountInputData inputData = new AccountInputData(username, method, bank);

        assertEquals(username, inputData.getUsername());
    }

    @Test
    public void testGetMethod() {
        String username = "jane_smith";
        String method = "deposit";
        String bank = "ABC Bank";

        AccountInputData inputData = new AccountInputData(username, method, bank);

        assertEquals(method, inputData.getMethod());
    }

    @Test
    public void testAccountInputDataGetBank() {
        String username = "test_user";
        String method = "withdraw";
        String bank = "Test Bank";

        AccountInputData inputData = new AccountInputData(username, method, bank);

        assertEquals(bank, inputData.getBank());
    }
    @Test
    public void testInequality() {
        String username1 = "user1";
        String method1 = "transfer";
        String bank1 = "Bank1";

        String username2 = "user2";
        String method2 = "withdraw";
        String bank2 = "Bank2";

        AccountInputData inputData1 = new AccountInputData(username1, method1, bank1);
        AccountInputData inputData2 = new AccountInputData(username2, method2, bank2);

        assertNotEquals(inputData1, inputData2);
    }

    @Test
    public void testOutputdataGetUsername() {
        String username = "john_doe";
        String method = "transfer";
        String bankFee = "10.00";
        boolean useCaseFailed = false;

        AccountOutputData outputData = new AccountOutputData(username, method, bankFee, useCaseFailed);

        assertEquals(username, outputData.getUsername());
    }

    @Test
    public void testOutputdataGetMethod() {
        String username = "jane_smith";
        String method = "deposit";
        String bankFee = "5.00";
        boolean useCaseFailed = false;

        AccountOutputData outputData = new AccountOutputData(username, method, bankFee, useCaseFailed);

        assertEquals(method, outputData.getMethod());
    }

    @Test
    public void testOutputdataGetBankFee() {
        String username = "test_user";
        String method = "withdraw";
        String bankFee = "8.50";
        boolean useCaseFailed = false;

        AccountOutputData outputData = new AccountOutputData(username, method, bankFee, useCaseFailed);

        assertEquals(bankFee, outputData.getBankFee());
    }
    @Test
    public void testLoginInputGetUsername() {
        String username = "john_doe";
        String password = "password123";

        LoginInputData inputData = new LoginInputData(username, password);

        assertEquals(username, inputData.getUsername());
    }

    @Test
    public void testLoginInputGetPassword() {
        String username = "jane_smith";
        String password = "securePassword";

        LoginInputData inputData = new LoginInputData(username, password);

        assertEquals(password, inputData.getPassword());
    }

    @Test
    public void testLoginInputEquality() {
        String username = "user1";
        String password = "pass123";

        LoginInputData inputData1 = new LoginInputData(username, password);
        LoginInputData inputData2 = new LoginInputData(username, password);

        assertNotEquals(inputData1, inputData2);
    }

    @Test
    public void testLoginInputInequality() {
        String username1 = "user1";
        String password1 = "pass123";

        String username2 = "user2";
        String password2 = "securePass";

        LoginInputData inputData1 = new LoginInputData(username1, password1);
        LoginInputData inputData2 = new LoginInputData(username2, password2);

        assertNotEquals(inputData1, inputData2);
    }
    @Test
    public void testLoginOutputDataGetUsername() {
        String username = "john_doe";
        String bank = "Test Bank";
        double balance = 1000.0;
        String[][] currencies = {{"USD", "500.0"}, {"EUR", "300.0"}};
        boolean useCaseFailed = false;

        LoginOutputData outputData = new LoginOutputData(username, bank, balance, currencies, useCaseFailed);

        assertEquals(username, outputData.getUsername());
    }

    @Test
    public void testLoginOutputDataGetCurrencies() {
        String username = "jane_smith";
        String bank = "Another Bank";
        double balance = 2500.0;
        String[][] currencies = {{"GBP", "1000.0"}, {"JPY", "700.0"}};
        boolean useCaseFailed = false;

        LoginOutputData outputData = new LoginOutputData(username, bank, balance, currencies, useCaseFailed);

        assertArrayEquals(currencies, outputData.getCurrencies());
    }

    @Test
    public void testLoginOutputDataGetBank() {
        String username = "test_user";
        String bank = "XYZ Bank";
        double balance = 500.0;
        String[][] currencies = {{"CAD", "200.0"}, {"AUD", "150.0"}};
        boolean useCaseFailed = false;

        LoginOutputData outputData = new LoginOutputData(username, bank, balance, currencies, useCaseFailed);

        assertEquals(bank, outputData.getBank());
    }

//    @Test
//    public void testLoginOutputDataGetBalance() {
//        String username = "user1";
//        String bank = "ABC Bank";
//        double balance = 1200.0;
//        String[][] currencies = {{"USD", "800.0"}, {"EUR", "300.0"}};
//        boolean useCaseFailed = false;
//
//        LoginOutputData outputData = new LoginOutputData(username, bank, balance, currencies, useCaseFailed);
//
//        assertEquals(balance, outputData.getBalance());
//    }

    @Test
    public void testSearchInputDataGetDate() {
        String date = "2023-01-01";
        String baseCurrency = "USD";
        String symbols = "EUR,GBP";

        SearchInputData inputData = new SearchInputData(date, baseCurrency, symbols);

        assertEquals(date, inputData.getDate());
    }

    @Test
    public void testSearchInputDataGetBaseCurrency() {
        String date = "2023-02-15";
        String baseCurrency = "EUR";
        String symbols = "USD,GBP";

        SearchInputData inputData = new SearchInputData(date, baseCurrency, symbols);

        assertEquals(baseCurrency, inputData.getBaseCurrency());
    }

    @Test
    public void testSearchInputDataGetSymbols() {
        String date = "2023-03-31";
        String baseCurrency = "GBP";
        String symbols = "USD,EUR";

        SearchInputData inputData = new SearchInputData(date, baseCurrency, symbols);

        assertEquals(symbols, inputData.getSymbols());
    }

    @Test
    public void testSearchInputDataInequality() {
        String date1 = "2023-05-01";
        String baseCurrency1 = "EUR";
        String symbols1 = "USD,GBP";

        String date2 = "2023-05-01";
        String baseCurrency2 = "USD";
        String symbols2 = "EUR,GBP";

        SearchInputData inputData1 = new SearchInputData(date1, baseCurrency1, symbols1);
        SearchInputData inputData2 = new SearchInputData(date2, baseCurrency2, symbols2);

        assertNotEquals(inputData1, inputData2);
    }
    @Test
    public void testSearchOutputDataGetAfterSymbols() {
        String afterSymbols = "USD,EUR";
        String afterCurrency = "EUR";
        boolean useCaseFailed = false;

        SearchOutputData outputData = new SearchOutputData(afterSymbols, afterCurrency, useCaseFailed);

        assertEquals(afterSymbols, outputData.get_afterSymbols());
    }

    @Test
    public void testSearchOutputDataGetAfterCurrency() {
        String afterSymbols = "EUR,GBP";
        String afterCurrency = "GBP";
        boolean useCaseFailed = false;

        SearchOutputData outputData = new SearchOutputData(afterSymbols, afterCurrency, useCaseFailed);

        assertEquals(afterCurrency, outputData.get_afterCurrency());
    }

    @Test
    public void testSearchOutputDataIsUseCaseFailed() {
        String afterSymbols = "USD,EUR";
        String afterCurrency = "EUR";
        boolean useCaseFailed = true;

        SearchOutputData outputData = new SearchOutputData(afterSymbols, afterCurrency, useCaseFailed);

        assertTrue(outputData.isUseCaseFailed());
    }

    @Test
    public void testSearchOutputDataIsNotUseCaseFailed() {
        String afterSymbols = "EUR,GBP";
        String afterCurrency = "GBP";
        boolean useCaseFailed = false;

        SearchOutputData outputData = new SearchOutputData(afterSymbols, afterCurrency, useCaseFailed);

        assertFalse(outputData.isUseCaseFailed());
    }
    @Test
    public void testConvertInputDataGetSymbolB() {
        String symbolB = "USD";
        String currencyB = "United States Dollar";
        String symbolA = "EUR";
        String username = "john_doe";

        ConvertInputData inputData = new ConvertInputData(symbolB, currencyB, symbolA, username);

        assertEquals(symbolB, inputData.getSymbolB());
    }

    @Test
    public void testConvertInputDataGetCurrencyB() {
        String symbolB = "EUR";
        String currencyB = "Euro";
        String symbolA = "GBP";
        String username = "jane_smith";

        ConvertInputData inputData = new ConvertInputData(symbolB, currencyB, symbolA, username);

        assertEquals(currencyB, inputData.getCurrencyB());
    }

    @Test
    public void testConvertInputDataGetSymbolA() {
        String symbolB = "GBP";
        String currencyB = "British Pound Sterling";
        String symbolA = "USD";
        String username = "test_user";

        ConvertInputData inputData = new ConvertInputData(symbolB, currencyB, symbolA, username);

        assertEquals(symbolA, inputData.getSymbolA());
    }

    @Test
    public void testConvertInputDataGetUsername() {
        String symbolB = "JPY";
        String currencyB = "Japanese Yen";
        String symbolA = "CNY";
        String username = "user123";

        ConvertInputData inputData = new ConvertInputData(symbolB, currencyB, symbolA, username);

        assertEquals(username, inputData.getUsername());
    }

    @Test
    public void testConvertOutputDataGetSymbolA() {
        String symbolA = "USD";
        String currencyA = "United States Dollar";
        double leftAmount = 500.0;
        String[][] currencies = {{"EUR", "250.0"}, {"GBP", "150.0"}};
        boolean useCaseFailed = false;

        ConvertOutputData outputData = new ConvertOutputData(symbolA, currencyA, leftAmount, currencies, useCaseFailed);

        assertEquals(symbolA, outputData.getSymbolA());
    }

    @Test
    public void testConvertOutputDataGetCurrencyA() {
        String symbolA = "EUR";
        String currencyA = "Euro";
        double leftAmount = 800.0;
        String[][] currencies = {{"USD", "400.0"}, {"GBP", "200.0"}};
        boolean useCaseFailed = false;

        ConvertOutputData outputData = new ConvertOutputData(symbolA, currencyA, leftAmount, currencies, useCaseFailed);

        assertEquals(currencyA, outputData.getCurrencyA());
    }
    @Test
    public void testConvertOutputDataGetCurrencies() {
        String symbolA = "JPY";
        String currencyA = "Japanese Yen";
        double leftAmount = 1000.0;
        String[][] currencies = {{"USD", "500.0"}, {"EUR", "300.0"}};
        boolean useCaseFailed = false;

        ConvertOutputData outputData = new ConvertOutputData(symbolA, currencyA, leftAmount, currencies, useCaseFailed);

        assertArrayEquals(currencies, outputData.getCurrencies());
    }

    @Test
    public void testConvertOutputDataIsUseCaseFailed() {
        String symbolA = "CNY";
        String currencyA = "Chinese Yuan";
        double leftAmount = 1500.0;
        String[][] currencies = {{"USD", "800.0"}, {"EUR", "400.0"}};
        boolean useCaseFailed = true;

        ConvertOutputData outputData = new ConvertOutputData(symbolA, currencyA, leftAmount, currencies, useCaseFailed);

        assertTrue(outputData.isUseCaseFailed());
    }

    @Test
    public void testConvertOutputDataIsNotUseCaseFailed() {
        String symbolA = "AUD";
        String currencyA = "Australian Dollar";
        double leftAmount = 2000.0;
        String[][] currencies = {{"USD", "1000.0"}, {"EUR", "500.0"}};
        boolean useCaseFailed = false;

        ConvertOutputData outputData = new ConvertOutputData(symbolA, currencyA, leftAmount, currencies, useCaseFailed);

        assertFalse(outputData.isUseCaseFailed());
    }

    @Test
    public void testCurrencyConverterCalculateExchange() throws IOException {
        // Create an instance of CurrencyConverter
        CurrencyConverter currencyConverter = new CurrencyConverter();

        // Test the calculateExchange method
        String givenAmount = "100.0";
        String currencyRate = "1.2"; // Assuming exchange rate
        double bankFee = 0.05; // Assuming bank fee

        String result = currencyConverter.calculateExchange(givenAmount, currencyRate, bankFee);
        assertEquals("115.0", result); // The expected result depends on the specific calculations in your code
    }
    @Test
    public void testSignupInputDataGetUsername() {
        String username = "john_doe";
        String password = "password123";
        String repeatPassword = "password123";
        Bank bank = new RBC();
        double initialBalance = 1000.0;
        String accountHolder = "John Doe";
        String pass = "pass123";

        SignupInputData inputData = new SignupInputData(username, password, repeatPassword, bank, initialBalance, accountHolder, pass);

        assertEquals(username, inputData.getUsername());
    }

    @Test
    public void testSignupInputDataGetPassword() {
        String username = "jane_smith";
        String password = "securePass";
        String repeatPassword = "securePass";
        Bank bank = new RBC();
        double initialBalance = 1500.0;
        String accountHolder = "Jane Smith";
        String pass = "pass456";

        SignupInputData inputData = new SignupInputData(username, password, repeatPassword, bank, initialBalance, accountHolder, pass);

        assertEquals(password, inputData.getPassword());
    }

    @Test
    public void testSignupInputDataGetRepeatPassword() {
        String username = "test_user";
        String password = "testPass123";
        String repeatPassword = "testPass123";
        Bank bank = new RBC();
        double initialBalance = 2000.0;
        String accountHolder = "Test User";
        String pass = "pass789";

        SignupInputData inputData = new SignupInputData(username, password, repeatPassword, bank, initialBalance, accountHolder, pass);

        assertEquals(repeatPassword, inputData.getRepeatPassword());
    }

    @Test
    public void testSignupInputDataGetBank() {
        String username = "user123";
        String password = "userPass";
        String repeatPassword = "userPass";
        Bank bank = new RBC();
        double initialBalance = 2500.0;
        String accountHolder = "User One";
        String pass = "pass987";

        SignupInputData inputData = new SignupInputData(username, password, repeatPassword, bank, initialBalance, accountHolder, pass);

        assertEquals(bank, inputData.getBank());
    }

    @Test
    public void testSignupInputDataGetAccountHolder() {
        String username = "user789";
        String password = "pass456";
        String repeatPassword = "pass456";
        Bank bank = new RBC();
        double initialBalance = 3500.0;
        String accountHolder = "User Three";
        String pass = "pass321";

        SignupInputData inputData = new SignupInputData(username, password, repeatPassword, bank, initialBalance, accountHolder, pass);

        assertEquals(accountHolder, inputData.getAccountHolder());
    }

    @Test
    public void testSignupInputDataGetPass() {
        String username = "user000";
        String password = "pass789";
        String repeatPassword = "pass789";
        Bank bank = new RBC();
        double initialBalance = 4000.0;
        String accountHolder = "User Four";
        String pass = "pass000";

        SignupInputData inputData = new SignupInputData(username, password, repeatPassword, bank, initialBalance, accountHolder, pass);

        assertEquals(pass, inputData.getPass());
    }
    @Test
    public void testSignupOutputDataGetUsername() {
        String username = "john_doe";
        boolean useCaseFailed = false;

        SignupOutputData outputData = new SignupOutputData(username, useCaseFailed);

        assertEquals(username, outputData.getUsername());
    }

    @Test
    public void testSignupOutputDataIsUseCaseFailed() {
        String username = "jane_smith";
        boolean useCaseFailed = true;

        SignupOutputData outputData = new SignupOutputData(username, useCaseFailed);

        assertTrue(outputData.isUseCaseFailed());
    }

    @Test
    public void testSignupOutputDataIsNotUseCaseFailed() {
        String username = "test_user";
        boolean useCaseFailed = false;

        SignupOutputData outputData = new SignupOutputData(username, useCaseFailed);

        assertFalse(outputData.isUseCaseFailed());
    }
    @Test
    public void testViewExchangeHistoryInputDataGetAccountHolder() {
        String accountHolder = "john_doe";

        ViewExchangeHistoryInputData inputData = new ViewExchangeHistoryInputData(accountHolder);

        assertEquals(accountHolder, inputData.getAccountHolder());
    }

    @Test
    public void testViewExchangeHistoryOutputDataGetExchangeHistories() {
        List<ExchangeHistory> histories = createSampleExchangeHistories();

        ViewExchangeHistoryOutputData outputData = new ViewExchangeHistoryOutputData(histories);

        assertEquals(histories, outputData.getExchangeHistories());
    }

    // Add more test cases as needed based on the behavior of your code

    // Helper method to create sample exchange histories for testing
    private List<ExchangeHistory> createSampleExchangeHistories() {
        List<ExchangeHistory> histories = new ArrayList<>();
        histories.add(new ExchangeHistory("USD", "EUR", 100.0, 1.2));
        histories.add(new ExchangeHistory("EUR", "GBP", 150.0, 0.8));
        // Add more sample histories if needed
        return histories;
    }
    @Test
    public void testExistsByCodeWithExistingCode() throws IOException {
        SearchInputData inputData = createSampleSearchInputData();
        SearchCurrency searchCurrency = new SearchCurrency(inputData);

        assertTrue(searchCurrency.existsByCode("USD"));
    }

    @Test
    public void testExistsByCodeWithNonExistingCode() throws IOException {
        SearchInputData inputData = createSampleSearchInputData();
        SearchCurrency searchCurrency = new SearchCurrency(inputData);

        assertFalse(searchCurrency.existsByCode("XYZ"));
    }

    @Test
    public void testGetExistingCode() throws IOException {
        SearchInputData inputData = createSampleSearchInputData();
        SearchCurrency searchCurrency = new SearchCurrency(inputData);

        assertEquals("USD", searchCurrency.get("USD"));
    }

    @Test
    public void testGetNonExistingCode() throws IOException {
        SearchInputData inputData = createSampleSearchInputData();
        SearchCurrency searchCurrency = new SearchCurrency(inputData);

        assertEquals("XYZ", searchCurrency.get("XYZ"));
    }

    // Add more test cases as needed based on the behavior of your code

    // Helper method to create sample search input data for testing
    private SearchInputData createSampleSearchInputData() {
        return new SearchInputData("2023-01-01", "EUR", "USD");
    }

}
