package thelameres.todolist.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import thelameres.todolist.core.data.models.SuperEntity;
import thelameres.todolist.core.data.models.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoRepositoryBean
public interface SuperRepository<T extends SuperEntity> extends JpaRepository<T, UUID> {
    List<T> findByCreatedBy(User user);

    List<T> findByLastModifiedBy(User user);

    List<T> findByCreatedDateBetween(LocalDateTime start, LocalDateTime end);

    List<T> findByLastModifiedDateBetween(LocalDateTime start,  LocalDateTime end);
}
