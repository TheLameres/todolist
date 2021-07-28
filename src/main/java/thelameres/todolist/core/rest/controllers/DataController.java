package thelameres.todolist.core.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

public interface DataController<T, ID> {
    @GetMapping("/{id}")
    T findById(@PathVariable("id") ID id);
    @GetMapping("/name/{name}")
    T findByName(@PathVariable("name") String name);
    @GetMapping
    List<T> findAll();
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    T save(@RequestBody T entity);
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    T save(@PathVariable("id")ID id, @RequestBody T entity);
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void delete(@PathVariable("id")ID id);
    @GetMapping("/count")
    Map<String, Integer> count();
}
