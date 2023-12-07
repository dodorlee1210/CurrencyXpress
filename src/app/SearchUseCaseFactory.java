package app;

import javax.swing.*;
import java.io.IOException;
import data_access.FileUserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.account.AccountController;
import interface_adapter.account.AccountViewModel;
import interface_adapter.search_exchangerate.SearchController;
import interface_adapter.search_exchangerate.SearchPresenter;
import interface_adapter.search_exchangerate.SearchViewModel;
import use_case.search_exchangerate.SearchInputBoundary;
import use_case.search_exchangerate.SearchInteractor;
import use_case.search_exchangerate.SearchOutputBoundary;
import view.SearchView;

public class SearchUseCaseFactory {
    private SearchUseCaseFactory() {}

    public static SearchView create(ViewManagerModel viewManagerModel,
                                    SearchViewModel searchViewModel,
                                    FileUserDataAccessObject dataAccessObject,
                                    AccountViewModel accountViewModel){
        try {
            SearchController searchController = createSearchUseCase(viewManagerModel, searchViewModel, dataAccessObject, accountViewModel);
            return new SearchView(searchViewModel, searchController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error in Factory");
        }
        return null;
    }

    private static SearchController createSearchUseCase(ViewManagerModel viewManagerModel,
                                                        SearchViewModel searchViewModel,

                                                        FileUserDataAccessObject dataAccessObject,
                                                        AccountViewModel accountViewModel) throws IOException {

        SearchOutputBoundary searchOutputBoundary = new SearchPresenter(viewManagerModel, searchViewModel, accountViewModel);
        SearchInputBoundary searchInteractor = new SearchInteractor(searchOutputBoundary, dataAccessObject);
        return new SearchController(searchInteractor);
    }

}


