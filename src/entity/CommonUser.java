package entity;

import entity.banks.Bank;

import java.time.LocalDateTime;

public class CommonUser implements User {

    private final String name;
    private final String password;
    private final LocalDateTime creationTime;

    private Account userAccount;

    CommonUser(String name, String password, LocalDateTime creationTime, Bank bank) {
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
        this.userAccount = new Account(name, bank);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}
