package thelameres.todolist.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import thelameres.todolist.core.data.models.Permission;

import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {


}