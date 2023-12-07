package entity;

import entity.banks.Bank;

public class CommonUserFactory implements UserFactory {

    /**
     * Create the user object from the given parameters
     * @param username          user's username to log in
     * @param password          user's password to log in
     * @param bank              the bank user is using
     * @param initialBalance    the initial balance when user is creating the account
     * @param accountHolder     user's name
     * @return the user object
     */
    @Override
    public User create(String username, String password, Bank bank, double initialBalance, String accountHolder) {
        return new CommonUser(username, password, bank, initialBalance, accountHolder);
    }
}
