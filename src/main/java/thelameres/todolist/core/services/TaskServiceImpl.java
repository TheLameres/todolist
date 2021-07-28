package thelameres.todolist.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import thelameres.todolist.core.data.models.Task;
import thelameres.todolist.core.repositories.TaskRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Component
public class TaskServiceImpl implements TaskService {
    TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Stream<Task> findAll(int offset, int limit) {
        return taskRepository.findAll(PageRequest.of(offset, limit)).stream();
    }

    @Override
    public Task findById(UUID uuid) {
        return taskRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Task findByName(String name) {
        return taskRepository.findByName(name);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public int count() {
        return (int) taskRepository.count();
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void delete(Task task) {
        taskRepository.delete(task);
    }

    @Override
    public void deleteById(UUID uuid) {
        taskRepository.deleteById(uuid);
    }
}
