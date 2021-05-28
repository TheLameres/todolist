package thelameres.todolist.core.data.responses;

import lombok.Data;

@Data
public class RegistrationRequest {
    String username;
    String password;
    String email;
}
