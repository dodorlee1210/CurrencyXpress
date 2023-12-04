package use_case.search_exchangerate;

public interface SearchOutputBoundary {
    void prepareSuccessView(SearchOutputData exchange);

    void prepareFailView(String error);
}
