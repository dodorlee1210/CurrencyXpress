package entity;

import java.time.LocalDateTime;
import entity.banks.Bank;

public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String name, String password, LocalDateTime ltd, Bank bank, String userID) {
        return new CommonUser(name, password, ltd, bank, userID);
    }
}
