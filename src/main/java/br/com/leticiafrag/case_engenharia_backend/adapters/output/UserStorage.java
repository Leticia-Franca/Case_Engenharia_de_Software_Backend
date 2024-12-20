package br.com.leticiafrag.case_engenharia_backend.adapters.output;

import br.com.leticiafrag.case_engenharia_backend.domain.User;
import br.com.leticiafrag.case_engenharia_backend.ports.output.UserOutputPort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserStorage implements UserOutputPort {

    private final List<User> savedUsers;

    public UserStorage() {
        this.savedUsers = new ArrayList<>();
    }

    @Override
    public User saveUser(User user) {
        /*
        TODO:
            Validate if user already exists, if so, it's an updateUser
            if not, it's a new user, then we just add it without
            replacing the user

            Generate an id for the user
        * */

        savedUsers.add(user);
        System.out.println("SAVEUSER: Users saved in 'savedUsers': ");
        savedUsers.forEach(currentUser -> {
            System.out.println("Name: " + currentUser.getName());
            System.out.println("Email: " + currentUser.getEmail());
        });
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return savedUsers;
    }

    @Override
    public User findById(Long id) {
        return savedUsers.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User updateUser(Long id, User user) {
        User existingUser = findById(id);
        if (existingUser == null) {
            return  null;
        }
        existingUser.setAge(user.getAge());
        existingUser.setEmail(user.getEmail());
        existingUser.setName(user.getName());

        return user;
    }

    @Override
    public boolean deleteUser(Long id) {
        /*
        * TODO:
        *  verify if an user with this id exists in the first place
        * if not, think about which is
        * better: to return false or to use an Exception?
        * */
        return this.savedUsers.removeIf(user ->
                user.getId().equals(id));
    }
}
