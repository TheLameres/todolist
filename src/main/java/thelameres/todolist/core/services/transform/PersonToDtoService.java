package thelameres.todolist.core.services.transform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thelameres.todolist.core.data.dto.PersonDto;
import thelameres.todolist.core.utils.mappers.PersonMapper;
import thelameres.todolist.core.services.PersonService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonToDtoService {
    @Autowired
    PersonService personService;
    @Autowired
    PersonMapper personMapper;


    public List<PersonDto> getAllPersons() {
        return personService.getAllPerson().stream().map(person ->
                personMapper.toDto(person))
                .collect(Collectors.toList());
    }

    public PersonDto getPerson(String uuid) {
        return personMapper.toDto(personService.getById(UUID.fromString(uuid)));
    }
}
