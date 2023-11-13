package entity;

public class PasswordChecker implements PasswordCheck{
    @Override
    public boolean passwordIsValid(String password) {
        return password != null && password.length() == 8;
    }
}
