package entity;

import entity.banks.Bank;

import java.time.LocalDateTime;

public interface UserFactory {
    User create(String name, String password, LocalDateTime ltd, Bank bank, String userID);
}
