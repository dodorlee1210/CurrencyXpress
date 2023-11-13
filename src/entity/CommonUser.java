package entity;

import java.time.LocalDateTime;

public class CommonUser implements User{

    private final String name;
    private final String password;
    private final LocalDateTime creationTime;

    CommonUser(String name, String password, LocalDateTime creationTime) {
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
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
