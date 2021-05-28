package thelameres.todolist.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import thelameres.todolist.core.data.models.Task;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
}