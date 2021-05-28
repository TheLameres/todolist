package thelameres.todolist.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import thelameres.todolist.core.data.models.Person;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    Person findByName(String name);

    @Query("select p from Person p where p.user.username = ?1")
    Optional<Person> findByUser(String name);

}