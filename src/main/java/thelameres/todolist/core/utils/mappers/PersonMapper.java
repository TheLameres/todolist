package thelameres.todolist.core.utils.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import thelameres.todolist.core.data.dto.PersonDto;
import thelameres.todolist.core.data.models.Person;
import thelameres.todolist.core.services.UserService;

import javax.annotation.PostConstruct;

@Component
public class PersonMapper extends AbstractMapper<Person, PersonDto> {

    @Autowired
    UserService userService;

    PersonMapper() {
        super(Person.class, PersonDto.class);
    }

    @Override
    void mapSpecificFields(Person source, PersonDto destination) {
        super.mapSpecificFields(source, destination);
        destination.setUser(source.getId().toString());
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Person.class, PersonDto.class)
                .addMappings(m -> m.skip(PersonDto::setUser)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(PersonDto.class, Person.class)
                .addMappings(m -> m.skip(Person::setUser)).setPostConverter(toEntityConverter());
    }
}
