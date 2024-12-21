package br.com.leticiafrag.case_engenharia_backend.adapters.input;
import br.com.leticiafrag.case_engenharia_backend.domain.User;
import br.com.leticiafrag.case_engenharia_backend.ports.input.UserInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/users", "/users/"})
public class Controller {

    @Autowired
    private UserInputPort userInputPort;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        String response = userInputPort.createUser(user);
        if (response.startsWith("ERROR:"))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> storedUsers = userInputPort.getAllUsers();
        if (storedUsers.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).build();
        else
            return ResponseEntity.status(HttpStatus.OK).body(storedUsers); //does this work?
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User searchedUser = userInputPort.getUserById(id);
        if (searchedUser == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            return ResponseEntity.status(HttpStatus.OK).body(searchedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody User user) {
        String response = userInputPort.updateUser(id, user);
        if (response.startsWith("ERROR:"))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        String response =  userInputPort.deleteUser(id);
        if (response.startsWith("ERROR:"))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        else
            return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
