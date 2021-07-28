package thelameres.todolist.core.repositories;

import thelameres.todolist.core.data.models.User;

import java.util.Optional;

public interface UserRepository extends SuperRepository<User> {
    Optional<User> findByUsername(String username);
}