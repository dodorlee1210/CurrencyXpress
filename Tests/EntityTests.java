
import entity.*;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import entity.banks.*;
import use_case.signup.SignupInputData;

public class EntityTests {

    @Test
    public void testConstructorSetsInitialValues() {
        String accountHolder = "John Doe";
        Bank bank = new BMO(); // Use BMO bank instead of creating a Bank object
        double balance = 100.0;

        Account account = new Account(accountHolder, bank, balance);

        assertEquals(accountHolder, account.getAccountHolder());
        assertEquals(bank, account.getBank());
        assertEquals(balance, account.getBalance(), 0.001);
    }

//    @Test
//    public void testGettersReturnCorrectValues() {
//        String accountHolder = "Jane Doe";
//        Bank bank = new BMO(); // Use BMO bank instead of creating a Bank object
//        double balance = 50.0;
//
//        Account account = new Account(accountHolder, bank, balance);
//
//        String newAccountHolder = "Jane Smith";
//        double newBalance = 75.0;
//
//        account.setAccountHolder(newAccountHolder);
//        account.setBalance(newBalance);
//
//        assertEquals(newAccountHolder, account.getAccountHolder());
//        assertEquals(newBalance, account.getBalance(), 0.001);
//    }


    @Test
    public void testAddExchangeHistoryAddsEntryToList() {
        ExchangeHistory history = new ExchangeHistory("USD", "EUR", 100.0, 1.1234);
        Account account = new Account("Alice", new CIBC(), 200.0);

        account.addExchangeHistory(history);

        List<ExchangeHistory> exchangeHistories = account.getExchangeHistory();

        assertTrue(exchangeHistories.contains(history));
    }

//    @Test
//    public void testGetExchangeHistoryReturnsCopyOfList() {
//        ExchangeHistory history = new ExchangeHistory("USD", "EUR", 100.0, 1.1234);
//        Account account = new Account("Bob", new BMO(), 300.0);
//
//        account.addExchangeHistory(history);
//
//        List<ExchangeHistory> exchangeHistories = account.getExchangeHistory();
//
//        exchangeHistories.remove(history);
//
//        assertFalse(account.getExchangeHistory().isEmpty());
//    }

    @Test(expected = NoSuchElementException.class)
    public void testGetExchangeHistoryIteratorThrowsExceptionForEmptyList() {
        Account account = new Account("Charlie", new BMO(), 400.0);

        Iterator<ExchangeHistory> iterator = account.getExchangeHistory().iterator();

        iterator.next();
    }

    @Test
    public void testSetForeignCurrencyUpdatesBalance() {
        Account account = new Account("David", new TD(), 500.0);

        account.setForeignCurrency("CAD", 50.0);

        assertEquals(50.0, account.getForeignCurrency("CAD"), 0.001);
    }

    @Test
    public void testGetForeignCurrencyReturnsZeroForNonexistentCurrency() {
        Account account = new Account("Edward", new TD(), 600.0);

        assertEquals(0.0, account.getForeignCurrency("AUD"), 0.001);
    }

    @Test
    public void testGetAllForeignCurrenciesReturnsCorrectData() {
        Account account = new Account("Francine", new Scotia(), 700.0);

        // Set foreign currency balances
        account.setForeignCurrency("EUR", 200.0);
        account.setForeignCurrency("GBP", 150.0);

        // Get all foreign currencies
        String[][] foreignCurrencies = account.getAllForeignCurrencies();

        // Verify the number of currencies
        assertEquals(2, foreignCurrencies.length);

        // Verify the first currency data
        assertArrayEquals(new String[]{"EUR", "200.0"}, foreignCurrencies[0]);

        // Verify the second currency data
        assertArrayEquals(new String[]{"GBP", "150.0"}, foreignCurrencies[1]);
    }
    @Test
    public void testUserFactoryCreatesValidUser() {
        String username = "test_user";
        String password = "secure_password";
        Bank bank = new BMO();
        double initialBalance = 100.0;
        String accountHolder = "Test User";

        // Inject a specific UserFactory implementation
        UserFactory userFactory = new CommonUserFactory();

        User user = userFactory.create(username, password, bank, initialBalance, accountHolder);

        // Verify user properties
        assertNotNull(user);
        assertEquals(username, user.getUsername());
        assertTrue(user.getPassword().equals(password));
        assertEquals(initialBalance, user.getUserAccount().getBalance(), 0.001);
    }
//    @Test(expected = IllegalArgumentException.class)
//    public void testConstructorThrowsExceptionForEmptyUsername() {
//        String password = "password123";
//        Bank bank = new BMO();
//        double initialBalance = 100.0;
//        String accountHolder = "John Doe";
//
//        new CommonUser("", password, bank, initialBalance, accountHolder);
//    }

    @Test
    public void testGettersReturnCorrectValues() {
        String username = "jane.doe";
        String password = "password456";
        Bank bank = new CIBC();
        double initialBalance = 200.0;
        String accountHolder = "Jane Doe";

        CommonUser user = new CommonUser(username, password, bank, initialBalance, accountHolder);

        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
    }

    @Test
    public void testUserCanAccessAndModifyUserAccount() {
        String username = "david.smith";
        String password = "password789";
        Bank bank = new TD();
        double initialBalance = 300.0;
        String accountHolder = "David Smith";

        CommonUser user = new CommonUser(username, password, bank, initialBalance, accountHolder);

        double newBalance = 400.0;
        user.getUserAccount().setBalance(newBalance);

        assertEquals(newBalance, user.getUserAccount().getBalance(), 0.001);
    }
    // Exchange History Unit Tests
    @Test
    public void testGetSourceCurrencyReturnsCorrectValue() {
        String expectedSourceCurrency = "USD";
        double exchangeRate = 1.1234;
        double exchangedAmount = 100.0;
        String targetCurrency = "EUR";

        ExchangeHistory history = new ExchangeHistory(expectedSourceCurrency, targetCurrency, exchangedAmount, exchangeRate);

        assertEquals(expectedSourceCurrency, history.getSourceCurrency());
    }
    // Exchange History Test
    @Test
    public void testGetTargetCurrencyReturnsCorrectValue() {
        String expectedTargetCurrency = "GBP";
        double exchangeRate = 0.8567;
        double exchangedAmount = 50.0;
        String sourceCurrency = "CAD";

        ExchangeHistory history = new ExchangeHistory(sourceCurrency, expectedTargetCurrency, exchangedAmount, exchangeRate);

        assertEquals(expectedTargetCurrency, history.getTargetCurrency());
    }

    @Test
    public void testGetExchangedAmountReturnsCorrectValue() {
        double expectedExchangedAmount = 200.0;
        double exchangeRate = 0.9876;
        String sourceCurrency = "JPY";
        String targetCurrency = "AUD";

        ExchangeHistory history = new ExchangeHistory(sourceCurrency, targetCurrency, expectedExchangedAmount, exchangeRate);

        assertEquals(expectedExchangedAmount, history.getExchangedAmount(), 0.001);
    }
    // Exchange History Test
    @Test
    public void testGetExchangeRateReturnsCorrectValue() {
        double expectedExchangeRate = 1.2345;
        double exchangedAmount = 150.0;
        String sourceCurrency = "CAD";
        String targetCurrency = "USD";

        ExchangeHistory history = new ExchangeHistory(sourceCurrency, targetCurrency, exchangedAmount, expectedExchangeRate);

        assertEquals(expectedExchangeRate, history.getExchangeRate(), 0.0001);
    }
    // Exchange History Test
    @Test
    public void testGetExchangeTimeReturnsNonNullValue() {
        double exchangeRate = 0.8765;
        double exchangedAmount = 250.0;
        String sourceCurrency = "GBP";
        String targetCurrency = "EUR";

        ExchangeHistory history = new ExchangeHistory(sourceCurrency, targetCurrency, exchangedAmount, exchangeRate);

        assertNotNull(history.getExchangeTime()); // checks if the returned time is not null
    }

//    @Test
//    public void testToStringReturnsFormattedString() {
//        String sourceCurrency = "JPY";
//        String targetCurrency = "AUD";
//        double exchangedAmount = 200.0;
//        double exchangeRate = 0.9876;
//        LocalDateTime exchangeTime = LocalDateTime.now();
//
//        ExchangeHistory history = new ExchangeHistory(sourceCurrency, targetCurrency, exchangedAmount, exchangeRate);
//
//        String expectedOutput = String.format("Source Currency: %s, Target Currency: %s, Exchanged Amount: %.2f, Exchange Rate: %.4f, Exchange Time: %s",
//                sourceCurrency, targetCurrency, exchangedAmount, exchangeRate, exchangeTime);
//
//        assertEquals(expectedOutput, history.toString());
//    }
    @Test
    public void testValidPasswordReturnsTrue() {
        String validPassword = "valid123";
        PasswordChecker checker = new PasswordChecker();

        assertTrue(checker.passwordIsValid(validPassword));
    }

    @Test
    public void testNullPasswordReturnsFalse() {
        String password = null;
        PasswordChecker checker = new PasswordChecker();

        assertFalse(checker.passwordIsValid(password));
    }

    @Test
    public void testShortPasswordReturnsFalse() {
        String password = "weak";
        PasswordChecker checker = new PasswordChecker();

        assertFalse(checker.passwordIsValid(password));
    }

    @Test
    public void testLongPasswordReturnsFalse() {
        String password = "invalid123456789";
        PasswordChecker checker = new PasswordChecker();

        assertFalse(checker.passwordIsValid(password));
    }
    // User Factory Test
    @Test
    public void testCreateReturnsValidUserWithValidData() {
        String username = "test_user";
        String password = "secure_password";
        Bank bank = new BMO();
        double initialBalance = 100.0;
        String accountHolder = "Test User";

        UserFactory userFactory = new CommonUserFactory();
        User user = userFactory.create(username, password, bank, initialBalance, accountHolder);

        assertNotNull(user);
        assertEquals(username, user.getUsername());
        assertTrue(user.getPassword().equals(password));
        assertEquals(initialBalance, user.getUserAccount().getBalance(), 0.001);
    }


//    @Test
//    public void testGetUsernameReturnsCorrectValue() {
//        String expectedUsername = "test_user";
//        User user = new UserFactory(expectedUsername, "secure_password", new UserFactory(100.0));
//
//        assertEquals(expectedUsername, user.getUsername());
//    }
//    @Test
//    public void testGetPasswordReturnsCorrectValue() {
//        String expectedPassword = "secret_password";
//        User user = new UserFactory("example_user", expectedPassword, new UserFactory(200.0));
//
//        assertTrue(user.getPassword().equals(expectedPassword));
//    }
//    @Test
//    public void testGetUserAccountReturnsCorrectObject() {
//        Account expectedAccount = new UserFactory(300.0);
//        User user = new UserFactory("admin_user", "admin123", expectedAccount) {
//        };
//
//        assertEquals(expectedAccount, user.getUserAccount());
//    }
    @Test
    public void testHasForeignCurrency_NoForeignCurrency_ReturnsFalse() {
        Account account = new Account("Test User", new BMO(), 100.0);

        assertFalse(account.hasForeignCurrency());
    }

    @Test
    public void testHasForeignCurrency_WithForeignCurrency_ReturnsTrue() {
        Account account = new Account("Example User", new TD(), 200.0);
        account.setForeignCurrency("EUR", 50.0);

        assertTrue(account.hasForeignCurrency());
    }
    @Test
    public void testHasForeignCurrency_NonExistentCurrencyCode_ReturnsFalse() {
        Account account = new Account("John Doe", new RBC(), 400.0);
        account.setForeignCurrency("JPY", 125.0);

        assertFalse(account.hasForeignCurrency("CAD"));
    }
    @Test
    public void testGetBankName_ReturnsBankName() {
        Account account = new Account("Jane Doe", new RBC(), 500.0);

        assertEquals("RBC", account.getBankName());
    }
    @Test
    public void testSetAccountHolder_UpdatesAccountHolderName() {
        Account account = new Account("Michael Smith", new BMO(), 100.0);
        String newAccountHolderName = "David Brown";

        account.setAccountHolder(newAccountHolderName);

        assertEquals(newAccountHolderName, account.getAccountHolder());
    }

    @Test
    public void testGetExchangeServiceFee_PositiveFee_ReturnsCorrectValue() {
        Bank bank = new BMO();
        double expectedFee = 0.025;

        assertEquals(expectedFee, bank.getExchangeServiceFee(), 0.0001);
    }
    @Test
    public void testGetBankName_ReturnsCorrectName() {
        Bank bank = new CIBC();
        String expectedName = "CIBC";

        assertEquals(expectedName, bank.getBankName());
    }
    @Test
    public void testGetExchangeServiceFee_ReturnsDefaultFee() {
        BMO bmo = new BMO();
        double expectedFee = 0.025;

        assertEquals(expectedFee, bmo.getExchangeServiceFee(), 0.0001);
    }
    @Test
    public void testGetBankName_ReturnsBMOName() {
        BMO bmo = new BMO();
        String expectedName = "BMO";

        assertEquals(expectedName, bmo.getBankName());
    }
    @Test
    public void testImplementsBankInterface_CorrectMethodsPresent() {
        BMO bmo = new BMO();

        assertTrue(bmo instanceof Bank);
        assertNotNull(bmo.getExchangeServiceFee());
        assertNotNull(bmo.getBankName());
    }
    @Test
    public void testGetExchangeServiceFee_ReturnsCIBCFee() {
        CIBC cibc = new CIBC();
        double expectedFee = 0.018;

        assertEquals(expectedFee, cibc.getExchangeServiceFee(), 0.0001);
    }
    @Test
    public void testGetBankName_ReturnsCIBCName() {
        CIBC cibc = new CIBC();
        String expectedName = "CIBC";

        assertEquals(expectedName, cibc.getBankName());
    }
    @Test
    public void testGetExchangeServiceFee_ReturnsRBCFee() {
        RBC rbc = new RBC();
        double expectedFee = 0.001;

        assertEquals(expectedFee, rbc.getExchangeServiceFee(), 0.0001);
    }
    @Test
    public void testGetBankName_ReturnsRBCName() {
        RBC rbc = new RBC();
        String expectedName = "RBC";

        assertEquals(expectedName, rbc.getBankName());
    }
    @Test
    public void testGetExchangeServiceFee_ReturnsScotiaFee() {
        Scotia scotia = new Scotia();
        double expectedFee = 0.02;

        assertEquals(expectedFee, scotia.getExchangeServiceFee(), 0.0001);
    }
    @Test
    public void testGetBankName_ReturnsScotiaName() {
        Scotia scotia = new Scotia();
        String expectedName = "Scotia";

        assertEquals(expectedName, scotia.getBankName());
    }
    @Test
    public void testGetExchangeServiceFee_ReturnsTDFee() {
        TD td = new TD();
        double expectedFee = 0.03;

        assertEquals(expectedFee, td.getExchangeServiceFee(), 0.0001);
    }
    @Test
    public void testGetBankName_ReturnsTDName() {
        TD td = new TD();
        String expectedName = "TD";

        assertEquals(expectedName, td.getBankName());
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


}
