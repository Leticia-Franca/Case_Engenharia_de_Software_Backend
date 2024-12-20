package br.com.leticiafrag.case_engenharia_backend.domain;

import br.com.leticiafrag.case_engenharia_backend.ports.input.UserInputPort;
import br.com.leticiafrag.case_engenharia_backend.ports.output.UserOutputPort;

import java.util.List;


public class UserService implements UserInputPort {

    private final UserOutputPort userOutputPort;

    public UserService(UserOutputPort userOutputPort) {
        this.userOutputPort = userOutputPort;
    }

    public User createUser(User user) {
        //some validation here
        return this.userOutputPort.saveUser(user);
    }

    public List<User> getAllUsers() {
        return this.userOutputPort.getAllUsers();
    }

    public User getUserById(Long id) {
        this.userOutputPort.findById(id);
        /*
        * TODO:
        *  if there isn't a user with the id informed,
        *  which is better: error message or exception?
        * */
        return null;
    }

    public User updateUser(Long id, User user) {
        this.userOutputPort.updateUser(id, user);
        return null;
    }

    public boolean deleteUser(Long id) {
        this.userOutputPort.deleteUser(id);
        return true;
    }
}
