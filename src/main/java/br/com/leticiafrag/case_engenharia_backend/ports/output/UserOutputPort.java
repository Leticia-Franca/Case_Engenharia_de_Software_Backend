package br.com.leticiafrag.case_engenharia_backend.ports.output;

import br.com.leticiafrag.case_engenharia_backend.domain.User;

import java.util.List;

/*
* Interface to be implemented by an output adapter (in this case,
* UserStorage)
* */
public interface UserOutputPort {

    User saveUser(User user);
    List<User> getAllUsers();
    User findById(String id);
    boolean findByEmail(String email);
    User updateUser(String id, User user);
    boolean deleteUser(String id);

}
