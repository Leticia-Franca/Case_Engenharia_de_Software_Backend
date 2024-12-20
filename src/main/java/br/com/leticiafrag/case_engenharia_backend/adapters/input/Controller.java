package br.com.leticiafrag.case_engenharia_backend.adapters.input;
import br.com.leticiafrag.case_engenharia_backend.domain.User;
import br.com.leticiafrag.case_engenharia_backend.ports.input.UserInputPort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class Controller {

    private UserInputPort userInputPort;

    @PostMapping
    public User createUser(@RequestBody User user) {
        System.out.println(user.getName());
        System.out.println(user.getEmail());
        System.out.println(user.getAge());
        return null;
        // returns the result from UserInputPort.createUser(user)
    }

    @GetMapping
    public List<User> getAllUsers() {
        // returns the result from UserInputPort.getAllUsers()
        return null;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        // returns the result from UserInputPort.getUserById(id)
        return null;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        // returns the result from UserInputPort.updateUser(id, user)
        return null;
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        // returns the result from UserInputPort.deleteUser(id)
        return true;
    }
}
