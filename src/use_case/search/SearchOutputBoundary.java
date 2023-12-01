package use_case.search;

import use_case.convert.ConvertOutputData;

public interface SearchOutputBoundary {
    void prepareSuccessView(SearchOutputData exchange);

    void prepareFailView(String error);
}
