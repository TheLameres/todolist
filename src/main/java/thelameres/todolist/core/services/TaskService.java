package thelameres.todolist.core.services;

import thelameres.todolist.core.data.models.Task;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public interface TaskService {
    Stream<Task> findAll(int offset, int limit);

    Task findById(UUID uuid);

    Task findByName(String name);

    List<Task> findAll();

    int count();

    Task save(Task task);

    Task update(Task task);

    void delete(Task task);

    void deleteById(UUID uuid);
}
