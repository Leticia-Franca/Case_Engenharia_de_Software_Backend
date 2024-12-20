package br.com.leticiafrag.case_engenharia_backend.ports.input;
import br.com.leticiafrag.case_engenharia_backend.domain.User;

import java.util.List;

public interface UserInputPort {

    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, User user);
    boolean deleteUser(Long id);
}
