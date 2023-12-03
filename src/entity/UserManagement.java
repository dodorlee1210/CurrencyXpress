package entity;

import entity.User;
import java.util.HashMap;

public class UserManagement {
    private final HashMap<String, User> userCollection = new HashMap<>(); // Map to store users by ID

    // Method to add a user to the user management system
    public void addUser(User user) {
        userCollection.put(user.getUsername(), user);
    }

    // Method to get a user by ID
    public User getUserByUsername(String username) {
        return userCollection.get(username);
    }

}
