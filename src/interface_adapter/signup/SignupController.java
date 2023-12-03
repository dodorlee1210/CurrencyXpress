package interface_adapter.signup;

import entity.banks.Bank;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

public class SignupController {

    final SignupInputBoundary userSignupUseCaseInteractor;
    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    public void execute(String username, String password1, String password2,
                        String bankName, double initialBalance, String accountHolder) {
        SignupInputData signupInputData = new SignupInputData(
                username, password1, password2, bankName, initialBalance, accountHolder);

        userSignupUseCaseInteractor.execute(signupInputData);
    }
}
