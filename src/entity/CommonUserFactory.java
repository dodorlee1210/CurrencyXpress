package entity;

import entity.banks.Bank;

public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String username, String password, String bankName, double initialBalance, String accountHolder) {
        return new CommonUser(username, password, bankName, initialBalance, accountHolder);
    }
}
