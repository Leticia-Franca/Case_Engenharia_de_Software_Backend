package br.com.leticiafrag.case_engenharia_backend;

import br.com.leticiafrag.case_engenharia_backend.adapters.input.Controller;
import br.com.leticiafrag.case_engenharia_backend.domain.User;
import br.com.leticiafrag.case_engenharia_backend.ports.input.UserInputPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.Collections;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Controller.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockHTTPRequests;

    @MockBean
    private UserInputPort userInputPort;

    @InjectMocks
    private Controller userController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser_WhenValidUser_ShouldReturnStatus201() throws Exception {
        User user = new User("Carol", "carol@example.com", 28);
        when(userInputPort.createUser(any(User.class))).thenReturn("User created successfully with ID: 1");

        mockHTTPRequests.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(content().string("User created successfully with ID: 1"));

        verify(userInputPort, times(1)).createUser(any(User.class));
    }

    @Test
    void testCreateUser_WhenInvalidUser_ShouldReturnStatus400() throws Exception {
        User invalidUser = new User("", "someemail-example.com", -42);
        when(userInputPort.createUser(any(User.class))).thenReturn("ERROR: The user is invalid.");

        mockHTTPRequests.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(invalidUser)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("ERROR: The user is invalid."));

        verify(userInputPort, times(1)).createUser(any(User.class));
    }

    @Test
    void testGetAllUsers_WhenTheresUsers_ShouldReturnStatus200() throws Exception {
        User user1 = new User("Alice", "alice@example.com", 25);
        User user2 = new User("Carlos", "carlos@example.com", 32);

        when(userInputPort.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        mockHTTPRequests.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[1].name").value("Carlos"));

        verify(userInputPort, times(1)).getAllUsers();
    }

    @Test
    void testGetAllUsers_WhenNoUsers_ShouldReturnStatus404() throws Exception {
        when(userInputPort.getAllUsers()).thenReturn(Collections.emptyList());

        mockHTTPRequests.perform(get("/users"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("ERROR: No users found."));
    }

    @Test
    void testGetUserById_WhenUserExists_ShouldReturnStatus200() throws Exception {
        User user = new User("Alice", "alice@example.com", 25);
        when(userInputPort.getUserById("1")).thenReturn(user);

        mockHTTPRequests.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Alice"));

        verify(userInputPort, times(1)).getUserById("1");
    }

    @Test
    void testGetUserById_WhenNoUsers_ShouldReturnStatus404() throws Exception{
        when(userInputPort.getUserById("1")).thenReturn(null);

        mockHTTPRequests.perform(get("/users/1"))
                .andExpect(status().isNotFound());

        verify(userInputPort, times(1)).getUserById("1");
    }

    @Test
    void testUpdateUser_WhenUserIsFound_ShouldReturnStatus200() throws Exception {
        User updatedUser = new User("Updated Name", "updated@example.com", 35);
        when(userInputPort.updateUser(eq("1"), any(User.class))).thenReturn("User updated successfully");

        mockHTTPRequests.perform(put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andExpect(content().string("User updated successfully"));

        verify(userInputPort, times(1)).updateUser(eq("1"), any(User.class));
    }

    @Test
    void testUpdateUser_WhenUserNotFound_ShouldReturnStatus404() throws Exception {
        when(userInputPort.updateUser(eq("1"), any(User.class)))
                .thenReturn("ERROR: Update could not be made. There's no user with this ID.");

        mockHTTPRequests.perform(put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(new User("Name", "email@example.com", 24))))
                .andExpect(status().isNotFound())
                .andExpect(content().string("ERROR: Update could not be made. There's no user with this ID."));

        verify(userInputPort, times(1)).updateUser(eq("1"), any(User.class));
    }

    @Test
    void testDeleteUser_WhenUserFound_ShouldReturnStatus200() throws Exception {
        when(userInputPort.deleteUser("1")).thenReturn("The user was deleted with success!");

        mockHTTPRequests.perform(delete("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("The user was deleted with success!"));

        verify(userInputPort, times(1)).deleteUser("1");
    }

    @Test
    void testDeleteUser_WhenUserNotFound_ShouldReturnStatus404() throws Exception {
        when(userInputPort.deleteUser("2")).thenReturn("ERROR: There's no user with this ID.");

        mockHTTPRequests.perform(delete("/users/2"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("ERROR: There's no user with this ID."));

        verify(userInputPort, times(1)).deleteUser("2");
    }

}
