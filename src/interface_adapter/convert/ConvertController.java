package interface_adapter.convert;

import use_case.convert.ConvertInputBoundary;
import use_case.convert.ConvertInputData;

public class ConvertController {
    final ConvertInputBoundary convertUseCaseInteractor;
    public ConvertController(ConvertInputBoundary convertUseCaseInteractor) {
        this.convertUseCaseInteractor = convertUseCaseInteractor;
    }

    public void execute(String symbolB, String currencyB, String symbolA) {
        ConvertInputData convertInputData = new ConvertInputData(symbolB, currencyB, symbolA);

        convertUseCaseInteractor.execute(convertInputData);
    }
}