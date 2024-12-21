package br.com.leticiafrag.case_engenharia_backend.domain;

import br.com.leticiafrag.case_engenharia_backend.ports.input.UserInputPort;
import br.com.leticiafrag.case_engenharia_backend.ports.output.UserOutputPort;

import java.util.List;

/*
* Service responsible managing users in the API.
* Implements the userInputPort interface (following the Hexagonal Architecture),
* providing methods to create, list, update and delete users (as well as
* data validation for the user being created).
*
* Note: the `get` methods return the stored data, while the other methods
* (create, update and delete) return messages that the Controller
* can integrate in the request response to inform the user in case of errors.
* */
public class UserService implements UserInputPort {

    private final UserOutputPort userOutputPort;

    public UserService(UserOutputPort userOutputPort) {
        this.userOutputPort = userOutputPort;
    }

    /*
    * Private method to validate user data.
    * It performs validations on the user's fields (name, email, age)
    * and returns an enum UserValidation representing the validation result.
    *
    * Validation logic:
    * - The name cannot be null or empty, and must be between 3 and 50 characters.
    * - The name must only contain letters and spaces.
    * - The email cannot be null or empty, and must have a valid format.
    * - The email must not be duplicated (it should not already exist in the system).
    * - The age must be a valid number between 0 and 123 years.
    *
    * @param user - The user to be validated
    * @return - A UserValidation enum representing the validation result.
    * */
    private UserValidation isUserValid(User user) {
        if (user.getName() == null || user.getName().isBlank())
            return UserValidation.NAME_EMPTY;
        if (user.getName().length() < 3 || user.getName().length() > 50)
            return UserValidation.NAME_LENGTH;
        if (!user.getName().matches("^[\\p{L}\\s]+$"))
            return UserValidation.NAME_INVALID;
        if (user.getEmail() == null || user.getEmail().isBlank())
            return UserValidation.EMAIL_EMPTY;
        if (!user.getEmail().matches("^[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,6}$"))
            return UserValidation.EMAIL_INVALID;
        if (userOutputPort.findByEmail(user.getEmail()))
            return UserValidation.EMAIL_DUPLICATED;
        if (user.getAge() < 0 || user.getAge() > 123)
            return UserValidation.AGE_INVALID;

        return UserValidation.VALID;
    }

    /*
    * Public method to create a new user in the system.
    * This method validates the user information before attempting
    * to save it. If the user is invalid, an error message from the
    * UserValidation enum is returned.
    * Otherwise, the user is saved and a success message is returned with
    * the user's ID.
    *
    * @param user - The user to be created.
    * @return A message indicating the success or failure of the creation.
    * */
    public String createUser(User user) {
        UserValidation result = isUserValid(user);
        if (result != UserValidation.VALID)
            return result.getMessage();
        User savedUser = this.userOutputPort.saveUser(user);
        return "User created successfully with ID: " + savedUser.getId();
    }

    /*
    * Method to retrieve all users in the system.
    *
    * @return A list containing all users (or an empty list if there
    * is no users in the system yet).
    * */
    public List<User> getAllUsers() {
        return this.userOutputPort.getAllUsers();
    }

    /*
    * Method to retrieve a user by their ID.
    *
    * @param id - The ID of the user to be retrieved.
    * @return The user with the specified ID, or null if not found.
    * */
    public User getUserById(String id) {
        return this.userOutputPort.findById(id);
    }

    /*
    * Method to update an existing user's data.
    * First, we try to update the user in the stored list and, if no user
    * with the given ID is found, an error message is returned. Otherwise,
    * a success message confirming the update is returned.
    *
    * @param id - The ID of the user to be updated.
    * @param user - The user object containing the updated data.
    * @return A success or error message.
    * */
    public String updateUser(String id, User user) {
        User updatedUser = this.userOutputPort.updateUser(id, user);
        if (updatedUser == null)
            return "ERROR: Update could not be made. There's no user with this ID.";
        return "User updated successfully";
    }

    /*
    * Method to delete a user by their ID.
    * If the user is found and deleted successfully, a success
    * message is returned. Otherwise, an error message indicating
    * that the user was not found is returned.
    *
    * @param id - The ID of the user to be deleted.
    * @return A success or error message.
    * */
    public String deleteUser(String id) {
        boolean isUserDeleted = this.userOutputPort.deleteUser(id);
        if (isUserDeleted)
            return "The user was deleted with success!";
        else
            return "ERROR: There's no user with this ID.";
    }
}
