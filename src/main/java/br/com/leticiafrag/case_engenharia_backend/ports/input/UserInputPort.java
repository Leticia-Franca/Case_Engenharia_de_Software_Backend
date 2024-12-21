package br.com.leticiafrag.case_engenharia_backend.ports.input;
import br.com.leticiafrag.case_engenharia_backend.domain.User;

import java.util.List;

public interface UserInputPort {

    String createUser(User user);
    List<User> getAllUsers();
    User getUserById(String id);
    String updateUser(String id, User user);
    boolean deleteUser(String id);
}
