package entity;

public class PasswordChecker implements PasswordCheck{
    /**
     * The method to verify the password validity
     * @param password password to check the validity
     * @return true if the password is valid, false otherwise.
     */
    @Override
    public boolean passwordIsValid(String password) {
        return password != null && password.length() == 8;
    }
}
