import data_access.FileUserDataAccessObject;
import entity.CommonUser;
import entity.User;
import entity.UserFactory;
import entity.banks.Bank;
import entity.banks.TD;
import interface_adapter.ViewManagerModel;
import interface_adapter.account.AccountViewModel;
import interface_adapter.convert.ConvertPresenter;
import interface_adapter.convert.ConvertViewModel;
import org.junit.Test;
import use_case.convert.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ConvertInteractorTests {
    @Test
    public void testConvertInteractorErrorCase1() throws IOException {
        ConvertDataAccessInterface convertDataAccessInterface = new CurrencyConverter();
        ConvertOutputBoundary convertOutputBoundary =
                new ConvertPresenter(new ViewManagerModel(), new ConvertViewModel(), new AccountViewModel());
        FileUserDataAccessObject dataAccessObject = new FileUserDataAccessObject("user.csv",
                (username, password, bank, initialBalance, accountHolder) ->
                        new CommonUser("seok", "1234", new TD(), 10000, "minseok"));

        ConvertInteractor convertInteractor = new ConvertInteractor(convertDataAccessInterface,
                convertOutputBoundary, dataAccessObject);

        String error = convertInteractor.execute(new ConvertInputData("KRW", "100", "CAD", "seok"));

        assertEquals("Error: Account does not have specified currency", error);
    }

    @Test
    public void testConvertInteractorErrorCase2() throws IOException {
        ConvertDataAccessInterface convertDataAccessInterface = new CurrencyConverter();
        ConvertOutputBoundary convertOutputBoundary =
                new ConvertPresenter(new ViewManagerModel(), new ConvertViewModel(), new AccountViewModel());
        FileUserDataAccessObject dataAccessObject = new FileUserDataAccessObject("user.csv",
                (username, password, bank, initialBalance, accountHolder) ->
                        new CommonUser("seok", "1234", new TD(), 10000, "minseok"));

        ConvertInteractor convertInteractor = new ConvertInteractor(convertDataAccessInterface,
                convertOutputBoundary, dataAccessObject);

        String error = convertInteractor.execute(new ConvertInputData("EUR", "100", "A10", "seok"));

        assertEquals("Error: Currency Code does not exist (A).", error);
    }

    @Test
    public void testConvertInteractorErrorCase3() throws IOException {
        ConvertDataAccessInterface convertDataAccessInterface = new CurrencyConverter();
        ConvertOutputBoundary convertOutputBoundary =
                new ConvertPresenter(new ViewManagerModel(), new ConvertViewModel(), new AccountViewModel());
        FileUserDataAccessObject dataAccessObject = new FileUserDataAccessObject("user.csv",
                (username, password, bank, initialBalance, accountHolder) ->
                        new CommonUser("seok", "1234", new TD(), 10000, "minseok"));

        ConvertInteractor convertInteractor = new ConvertInteractor(convertDataAccessInterface,
                convertOutputBoundary, dataAccessObject);

        String error = convertInteractor.execute(new ConvertInputData("EUR", "aa", "CAD", "seok"));

        assertEquals("Error: Currency has non-numerical values in it.", error);
    }

    @Test
    public void testConvertInteractorErrorCase4() throws IOException {
        ConvertDataAccessInterface convertDataAccessInterface = new CurrencyConverter();
        ConvertOutputBoundary convertOutputBoundary =
                new ConvertPresenter(new ViewManagerModel(), new ConvertViewModel(), new AccountViewModel());
        FileUserDataAccessObject dataAccessObject = new FileUserDataAccessObject("user.csv",
                (username, password, bank, initialBalance, accountHolder) ->
                        new CommonUser("seok", "1234", new TD(), 10000, "minseok"));

        ConvertInteractor convertInteractor = new ConvertInteractor(convertDataAccessInterface,
                convertOutputBoundary, dataAccessObject);

        String error = convertInteractor.execute(new ConvertInputData("EUR", "100", "EUR", "seok"));

        assertEquals("Error: Exchange does not happen for same currency codes.", error);
    }

    @Test
    public void testConvertInteractorErrorCase5() throws IOException {
        ConvertDataAccessInterface convertDataAccessInterface = new CurrencyConverter();
        ConvertOutputBoundary convertOutputBoundary =
                new ConvertPresenter(new ViewManagerModel(), new ConvertViewModel(), new AccountViewModel());
        FileUserDataAccessObject dataAccessObject = new FileUserDataAccessObject("user.csv",
                (username, password, bank, initialBalance, accountHolder) ->
                        new CommonUser("seok", "1234", new TD(), 10000, "minseok"));

        ConvertInteractor convertInteractor = new ConvertInteractor(convertDataAccessInterface,
                convertOutputBoundary, dataAccessObject);

        String error = convertInteractor.execute(new ConvertInputData("EUR", "1000000", "CAD", "seok"));

        assertEquals("Error: Account does not have enough balance to exchange", error);
    }

    @Test
    public void testConvertInteractorErrorCase6() throws IOException {
        ConvertDataAccessInterface convertDataAccessInterface = new CurrencyConverter();
        ConvertOutputBoundary convertOutputBoundary =
                new ConvertPresenter(new ViewManagerModel(), new ConvertViewModel(), new AccountViewModel());
        FileUserDataAccessObject dataAccessObject = new FileUserDataAccessObject("user.csv",
                (username, password, bank, initialBalance, accountHolder) ->
                        new CommonUser("seok", "1234", new TD(), 10000, "minseok"));

        ConvertInteractor convertInteractor = new ConvertInteractor(convertDataAccessInterface,
                convertOutputBoundary, dataAccessObject);

        String error = convertInteractor.execute(new ConvertInputData("EUR", "1000000", "CAD", "seok"));

        assertEquals("Error: Account does not have enough balance to exchange", error);
    }

}
