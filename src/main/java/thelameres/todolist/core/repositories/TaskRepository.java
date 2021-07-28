package thelameres.todolist.core.repositories;

import thelameres.todolist.core.data.models.Task;

public interface TaskRepository extends SuperRepository<Task> {
    Task findByName(String name);
}