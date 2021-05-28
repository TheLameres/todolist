package thelameres.todolist.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import thelameres.todolist.core.data.models.Task;
import thelameres.todolist.core.repositories.TaskRepository;

import java.util.List;
import java.util.stream.Stream;

@Component
public class TaskService {
    TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Stream<Task> findAll(int offset, int limit) {
        return taskRepository.findAll(PageRequest.of(offset, limit)).stream();
    }
    public List<Task> findAll1() {
        return taskRepository.findAll();
    }

    public int count() {
        return (int) taskRepository.count();
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public Task updata(Task task){
        return taskRepository.save(task);
    }

    public void delete(Task task){
        taskRepository.delete(task);
    }
}
