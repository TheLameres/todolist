package thelameres.todolist.core.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thelameres.todolist.core.data.models.Task;
import thelameres.todolist.core.services.TaskService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskController implements DataController<Task, UUID> {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public Task findById(UUID uuid) {
        return taskService.findById(uuid);
    }

    @Override
    public Task findByName(String name) {
        return taskService.findByName(name);
    }

    @Override
    public List<Task> findAll() {
        return taskService.findAll();
    }

    @Override
    public Task save(Task entity) {
        return taskService.save(entity);
    }

    @Override
    public Task save(UUID uuid, Task entity) {
        return taskService.save(entity);
    }

    @Override
    public void delete(UUID uuid) {
        taskService.deleteById(uuid);
    }

    @Override
    public Map<String, Integer> count() {
        return Collections.singletonMap("count", taskService.count());
    }
}
