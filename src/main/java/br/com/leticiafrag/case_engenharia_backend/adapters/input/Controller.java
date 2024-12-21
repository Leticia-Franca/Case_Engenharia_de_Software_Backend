package br.com.leticiafrag.case_engenharia_backend.adapters.input;
import br.com.leticiafrag.case_engenharia_backend.domain.User;
import br.com.leticiafrag.case_engenharia_backend.ports.input.UserInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*
* Controller class responsible for handling HTTP requests related to user
* operations. It exposes endpoints for creating, retrieving, updating and
* deleting users.
* The class interacts with the UserInputPort, which is injected by Spring
* and provides the contract for the service layer. The actual implementation
* of this interface is provided by the UserService class.
* This design (based on Hexagonal Architecture) ensures loose coupling between
* the controller and the business logic, as the controller does not depend on a
* specific implementation but rather on an abstraction.
* The Controller returns appropriate HTTP responses based on the outcome
* of the operations.
*
* The responses include:
* - Success messages with corresponding HTTP status codes (201 created, 200 OK)
* - Error messages with corresponding HTTP status codes (400 Bad Request, 404 Not Found)
*
* */
@RestController
@RequestMapping(value = {"/users", "/users/"})
public class Controller {

    @Autowired
    private UserInputPort userInputPort;

    /*
    * Created a new user.
    * This method receives user data in the request body and then calls the
    * createUser method from the UserInputPort to store the user in the system.
    * If the user data is invalid, an error response with a bad request status (400)
    * is returned.
    * If the user is created, a success response with a created status (201)
    * is returned.
    *
    * @param user - The user data to be created.
    * @return - ResponseEntity with either a success message or an error message.
    * */
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        String response = userInputPort.createUser(user);
        if (response.startsWith("ERROR:"))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /*
    * Retrive all users.
    * This method calls the getAllUsers method from the UserInputPort to fetch
    * all users from the system.
    *
    * @return - ResponseEntity with a list of all users or an error message if no
    * users are found.
    * */
    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        List<User> storedUsers = userInputPort.getAllUsers();
        if (storedUsers.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR: No users found.");
        else
            return ResponseEntity.status(HttpStatus.OK).body(storedUsers);
    }

    /*
    * Retrives a user by their ID.
    * This method calls the getUserById method from UserInputPort to fetch
    * a user with the specified ID.
    *
    * @param id - The ID of the user to be retrieved.
    * @return - ResponseEntity with the user data or an error message if the
    * user is not found.
    * */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable String id) {
        User searchedUser = userInputPort.getUserById(id);
        if (searchedUser == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR: User with id " + id + " not found.");
        else
            return ResponseEntity.status(HttpStatus.OK).body(searchedUser);
    }

    /*
    * Updates an existing user.
    * This method receives updated user data in the request body, and then calls the
    * updateUser method from the UserInputPort to update the user's information.
    *
    * @param id - The ID of the user to be updated.
    * @param user - The updated user data.
    * @return - ResponseEntity with either a success message or an error message
    * depending on the result.
    * */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody User user) {
        String response = userInputPort.updateUser(id, user);
        if (response.startsWith("ERROR:"))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /*
    * Deletes a user by their ID.
    * This method calls the deleteUser method from the UserInputPort to
    * delete a user with the specified ID.
    *
    * @param id - The ID of the user to be deleted.
    * @return - ResponseEntity with either a success message or an error message depending
    * on the result.
    * */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        String response =  userInputPort.deleteUser(id);
        if (response.startsWith("ERROR:"))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        else
            return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
