package thelameres.todolist.core.services;

import thelameres.todolist.core.data.models.User;
import thelameres.todolist.core.data.responses.AuthRequest;
import thelameres.todolist.core.data.responses.RegistrationRequest;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface UserService {
    User getUserById(UUID uuid);

    List<User> getAllUsers();

    User findById(UUID uuid);

    User findByLogin(String login);

    User findByLoginAndPassword(String login, String password);

    User findByLoginAndPassword(AuthRequest authRequest);

    User save(@NotNull String username, @NotNull String password, @NotNull String email,
              String firstName, String lastName, LocalDate birthDate, String position);

    User save(RegistrationRequest request);

    User save(User user);

    void delete(UUID uuid);

    long count();
}
