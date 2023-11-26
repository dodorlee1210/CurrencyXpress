package use_case.convert;

public interface ConvertOutputBoundary {
    void prepareSuccessView(ConvertOutputData exchange);

    void prepareFailView(String error);
}
