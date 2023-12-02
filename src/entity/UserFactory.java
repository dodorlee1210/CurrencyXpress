package entity;

import entity.banks.Bank;

import java.time.LocalDateTime;

public interface UserFactory {
    User create(String username, String password, Bank bank, double initialBalance, String accountHolder);
}
