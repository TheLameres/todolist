package thelameres.todolist.core.repositories;

import thelameres.todolist.core.data.models.Permission;

import java.util.Optional;

public interface PermissionRepository extends SuperRepository<Permission> {
    Optional<Permission> findByName(String name);
}