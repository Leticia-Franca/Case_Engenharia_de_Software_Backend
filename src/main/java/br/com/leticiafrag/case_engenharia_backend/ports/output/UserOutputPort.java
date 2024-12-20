package br.com.leticiafrag.case_engenharia_backend.ports.output;

import br.com.leticiafrag.case_engenharia_backend.domain.User;

import java.util.List;

public interface UserOutputPort {

    User saveUser(User user);
    List<User> getAllUsers();
    User findById(Long id);
    User updateUser(Long id, User user);
    boolean deleteUser(Long id);

}
