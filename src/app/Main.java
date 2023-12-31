package app;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.account.AccountViewModel;
import interface_adapter.convert.ConvertViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.search_exchangerate.SearchViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.convert.CurrencyConverter;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("CurrencyXpress");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will be observed by the Views.
        LoginViewModel loginViewModel = new LoginViewModel();
        AccountViewModel accountViewModel = new AccountViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        ConvertViewModel convertViewModel = new ConvertViewModel();
        SearchViewModel searchViewModel = new SearchViewModel();

        FileUserDataAccessObject userDataAccessObject;
        CurrencyConverter convertDataAccessObject;

        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            convertDataAccessObject = new CurrencyConverter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel,
                accountViewModel, signupViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        AccountView accountView = AccountUseCaseFactory.create(viewManagerModel, accountViewModel, convertViewModel,
                loginViewModel, searchViewModel);
        views.add(accountView, accountView.viewName);

        ConvertView convertView = ConvertUseCaseFactory.create(viewManagerModel, convertViewModel, accountViewModel,
                convertDataAccessObject, userDataAccessObject);
        views.add(convertView, convertView.viewName);

        SearchView searchView = SearchUseCaseFactory.create(viewManagerModel, searchViewModel, userDataAccessObject, accountViewModel);
        views.add(searchView, searchView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}