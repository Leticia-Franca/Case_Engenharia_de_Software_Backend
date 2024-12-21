package br.com.leticiafrag.case_engenharia_backend.adapters.output;

import br.com.leticiafrag.case_engenharia_backend.domain.User;
import br.com.leticiafrag.case_engenharia_backend.ports.output.UserOutputPort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserStorage implements UserOutputPort {

    private final List<User> savedUsers;

    public UserStorage() {
        this.savedUsers = new ArrayList<>();
    }

    @Override
    public User saveUser(User user) {

        savedUsers.add(user);
        //
        savedUsers.forEach(currentUser -> {
            System.out.println("Name: " + currentUser.getName());
            System.out.println("Email: " + currentUser.getEmail());
        });
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        if (savedUsers.isEmpty())
            return new ArrayList<>();
        return savedUsers;
    }

    @Override
    public User findById(String id) {
        return savedUsers.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean findByEmail(String email) {
        User searchedUser = savedUsers.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
        return searchedUser != null;
    }

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

    @Override
    public boolean deleteUser(String id) {
        return this.savedUsers.removeIf(user ->
                user.getId().equals(id));
    }
}
