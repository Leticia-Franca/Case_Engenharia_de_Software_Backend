package br.com.leticiafrag.case_engenharia_backend.adapters.output;

import br.com.leticiafrag.case_engenharia_backend.domain.User;
import br.com.leticiafrag.case_engenharia_backend.ports.output.UserOutputPort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/*
* UserStorage is an implementation of the UserOutputPort interface.
* It provides methods to interact with an in-memory list of users.
* This class acts as temporary storage mechanism for user data, simulating
* a persistent storage layer.
* */
@Component
public class UserStorage implements UserOutputPort {

    private final List<User> savedUsers;

    public UserStorage() {
        this.savedUsers = new ArrayList<>();
    }

    /*
    * Saves the provided new user to the in-memory list of users.
    *
    * @param user - The user to be saved.
    * @return - The saved user.
    * */
    @Override
    public User saveUser(User user) {
        savedUsers.add(user);
        return user;
    }

    /*
    * Retrieves all saved users and return them.
    * If there are no users saved, returns an empty list.
    *
    * @return - A list of all saved users.
    * */
    @Override
    public List<User> getAllUsers() {
        if (savedUsers.isEmpty())
            return new ArrayList<>();
        return savedUsers;
    }

    /*
    * Finds a user by their ID.
    * This method searches the saved users list for a user with the specified ID.
    * If no user is found, it returns null.
    *
    * @param id - The ID of the user to be searched.
    * @return - The user with the specified ID or null if not found.
    * */
    @Override
    public User findById(String id) {
        return savedUsers.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /*
    * Checks if a user exists with the specified email.
    * This method searches the saved users list for a user with the given email.
    * If a user with that email exists, it returns true; otherwise, it returns false.
    *
    * @param email - The email to be searched for.
    * @return - True if a user with the email exists, false otherwise.
    * */
    @Override
    public boolean findByEmail(String email) {
        User searchedUser = savedUsers.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
        return searchedUser != null;
    }

    /*
    * Updates an existing user.
    * This method finds the user by their ID and updates their name, email, and age
    * based on the provided user data. If the user with the specified ID does not
    * exist, it returns null.
    *
    * @param id - The ID of the user to be updated.
    * @param user - The new user data.
    * @return - The updated user or null if the user does not exist.
    * */
    @Override
    public User updateUser(String id, User user) {
        User existingUser = findById(id);
        if (existingUser == null) {
            return null;
        }
        existingUser.setAge(user.getAge());
        existingUser.setEmail(user.getEmail());
        existingUser.setName(user.getName());

        return user;
    }

    /*
    * Deletes a user by their ID.
    * This method removes the user with the specified ID from the saved users list.
    * If the user is successfully removed, it returns true. Otherwise, it returns false.
    *
    * @param id - The ID of the user to be deleted.
    * @return - True if the user was successfully deleted, false otherwise.
    * */
    @Override
    public boolean deleteUser(String id) {
        return this.savedUsers.removeIf(user ->
                user.getId().equals(id));
    }
}
