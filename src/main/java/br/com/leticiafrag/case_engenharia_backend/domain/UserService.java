package br.com.leticiafrag.case_engenharia_backend.domain;

import br.com.leticiafrag.case_engenharia_backend.ports.input.UserInputPort;
import br.com.leticiafrag.case_engenharia_backend.ports.output.UserOutputPort;

import java.util.List;

public class UserService implements UserInputPort {

    private final UserOutputPort userOutputPort;

    public UserService(UserOutputPort userOutputPort) {
        this.userOutputPort = userOutputPort;
    }

    // returns to createUser function an enum with the code and message informing the potential error in user information
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

    //validates user and returns an error or success message to Controller
    public String createUser(User user) {
        UserValidation result = isUserValid(user);
        if (result != UserValidation.VALID)
            return result.getMessage();
        User savedUser = this.userOutputPort.saveUser(user);
        return "User created successfully with ID: " + savedUser.getId();
    }

    public List<User> getAllUsers() {
        return this.userOutputPort.getAllUsers();
    }

    public User getUserById(String id) {
        return this.userOutputPort.findById(id);
    }

    public String updateUser(String id, User user) {
        User updatedUser = this.userOutputPort.updateUser(id, user);
        if (updatedUser == null)
            return "ERROR: Update could not be made. There's no user with this ID.";
        return "User updated successfully";
    }

    public String deleteUser(String id) {
        boolean isUserDeleted = this.userOutputPort.deleteUser(id);
        if (isUserDeleted)
            return "The user was deleted with success!";
        else
            return "ERROR: There's no user with this ID.";
    }
}
