package br.com.leticiafrag.case_engenharia_backend.adapters.input;
import br.com.leticiafrag.case_engenharia_backend.domain.User;
import br.com.leticiafrag.case_engenharia_backend.ports.output.UserOutputPort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class Controller {

    private UserOutputPort userOutputPort;

    @PostMapping
    public User createUser(@RequestBody User user) {
        // returns the result from userOutputPort.createUser(user)
    }

    @GetMapping
    public List<User> getAllUsers() {
        // returns the result from userOutputPort.getAllUsers()
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        // retorna o resultado do userOutputPort.getUserById(id)
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        // retorna o resultado do userOutputPort.updateUser(id, user)
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        // retorna o resultado do userOutputPort.deleteUser(id)
    }
}
