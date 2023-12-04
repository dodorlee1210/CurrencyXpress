package use_case.account;

import entity.User;

public interface AccountUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);
}
