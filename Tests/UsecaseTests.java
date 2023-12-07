
import entity.*;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import entity.banks.*;
import use_case.account.AccountInputData;
import use_case.account.AccountOutputData;
import use_case.login.LoginInputData;
import use_case.login.LoginOutputData;

public class UsecaseTests {
    @Test
    public void testGetUsername() {
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
    public void testGetBank() {
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

    @Test
    public void testLoginOutputDataGetBalance() {
        String username = "user1";
        String bank = "ABC Bank";
        double balance = 1200.0;
        String[][] currencies = {{"USD", "800.0"}, {"EUR", "300.0"}};
        boolean useCaseFailed = false;

        LoginOutputData outputData = new LoginOutputData(username, bank, balance, currencies, useCaseFailed);

        assertEquals(balance, outputData.getBalance());
    }

}
