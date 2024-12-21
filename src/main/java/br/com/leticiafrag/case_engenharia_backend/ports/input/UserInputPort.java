package br.com.leticiafrag.case_engenharia_backend.ports.input;
import br.com.leticiafrag.case_engenharia_backend.domain.User;

import java.util.List;

/*
 * Interface to be implemented by the service layer (in this case,
 * UserService)
 * */
public interface UserInputPort {

    String createUser(User user);
    List<User> getAllUsers();
    User getUserById(String id);
    String updateUser(String id, User user);
    String deleteUser(String id);
}
