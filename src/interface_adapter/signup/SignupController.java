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
                        Bank bank, double initialBalance, String accountHolder, String pass) {
        SignupInputData signupInputData = new SignupInputData(
                username, password1, password2, bank, initialBalance, accountHolder, pass);

        userSignupUseCaseInteractor.execute(signupInputData);
    }
}
