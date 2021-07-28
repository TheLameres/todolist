package thelameres.todolist.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import thelameres.todolist.core.data.models.Person;
import thelameres.todolist.core.data.models.User;
import thelameres.todolist.core.data.responses.AuthRequest;
import thelameres.todolist.core.data.responses.RegistrationRequest;
import thelameres.todolist.core.repositories.RoleRepository;
import thelameres.todolist.core.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Component
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User getUserById(UUID uuid) {
        return userRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(UUID uuid) {
        return userRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByUsername(login).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        var user = findByLogin(login);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }

    }

    @Override
    public User findByLoginAndPassword(AuthRequest authRequest) {
        return findByLoginAndPassword(authRequest.getLogin(), authRequest.getPassword());
    }

    @Override
    public User save(@NotNull String username, @NotNull String password, @NotNull String email,
                     String firstName, String lastName, LocalDate birthDate, String position) {
        return save(User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .person(Person.builder().
                        firstName(firstName)
                        .lastName(lastName)
                        .position(position)
                        .birthDate(birthDate).build())
                .role(roleRepository.findByName("ROLE_USER")
                        .orElseThrow(EntityNotFoundException::new))
                .enabled(true).build());
    }

    @Override
    public User save(RegistrationRequest request) {
        return save(request.getUsername(),
                request.getPassword(),
                request.getEmail(),
                request.getPerson().getFirstName(),
                request.getPerson().getLastName(),
                LocalDate.parse(request.getPerson().getBirthDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                request.getPerson().getPosition());
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(UUID uuid) {
        userRepository.deleteById(uuid);
    }

    @Override
    public long count() {
        return userRepository.count();
    }
}
