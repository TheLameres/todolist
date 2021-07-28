package thelameres.todolist.core.repositories;

import thelameres.todolist.core.data.models.Role;

import java.util.Optional;

public interface RoleRepository extends SuperRepository<Role> {
    Optional<Role> findByName(String name);
}