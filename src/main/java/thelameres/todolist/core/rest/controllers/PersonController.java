package thelameres.todolist.core.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import thelameres.todolist.core.data.dto.PersonDto;
import thelameres.todolist.core.services.transform.PersonToDtoService;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    PersonToDtoService personToDtoService;

    @GetMapping
    @RolesAllowed(value = "admin")
    public List<PersonDto> getAllPersons() {
        return personToDtoService.getAllPersons();
    }

    @GetMapping("/{id}")
    public PersonDto getPerson(@PathVariable("id") String uuid) {
        try {
            return personToDtoService.getPerson(uuid);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person with id: " + uuid + " not found");
        }
    }
}
