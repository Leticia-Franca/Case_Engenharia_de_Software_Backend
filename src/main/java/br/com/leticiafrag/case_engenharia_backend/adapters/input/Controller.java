package br.com.leticiafrag.case_engenharia_backend.adapters.input;
import br.com.leticiafrag.case_engenharia_backend.domain.User;
import br.com.leticiafrag.case_engenharia_backend.ports.input.UserInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class Controller {

    @Autowired
    private UserInputPort userInputPort;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userInputPort.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        // returns the result from UserInputPort.getAllUsers()
        //return null;
        return userInputPort.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        // returns the result from UserInputPort.getUserById(id)
        //return null;
        return userInputPort.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        // returns the result from UserInputPort.updateUser(id, user)
        //return null;
        return userInputPort.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        // returns the result from UserInputPort.deleteUser(id)
        //return true;
        return userInputPort.deleteUser(id);
    }
}
