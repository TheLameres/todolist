package thelameres.todolist.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thelameres.todolist.core.data.models.Person;
import thelameres.todolist.core.repositories.PersonRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    PersonRepository personRepository;

    @Autowired
    PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person getById(UUID uuid) {
        return personRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
    }

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    public Person editPerson(UUID uuid, Person person) {
        Optional<Person> personOptional = personRepository.findById(uuid);
        Person person2;
        if (personOptional.isPresent()) {
            person2 = personOptional.get();
            person2.setName(person.getName());
            return personRepository.save(person2);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void deletePerson(Person person) {
        personRepository.delete(person);
    }

    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    public Optional<Person> getPersonByUsernameInUser(String name) {
        return personRepository.findByUser(name);
    }
}
