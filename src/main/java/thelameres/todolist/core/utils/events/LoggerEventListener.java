package thelameres.todolist.core.utils.events;

import lombok.extern.slf4j.Slf4j;
import thelameres.todolist.core.data.models.SuperEntity;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Slf4j
public class LoggerEventListener implements EventListener {

    @Override
    @PrePersist
    public void prePersist(SuperEntity superEntity) {
        log.info("Try to persist {}", superEntity);
    }

    @Override
    @PostPersist
    public void postPersist(SuperEntity superEntity) {
        log.info("Persisted {}", superEntity);
    }

    @Override
    @PreUpdate
    public void preUpdate(SuperEntity superEntity) {
        log.info("Try to Update {}", superEntity);
    }

    @Override
    @PostUpdate
    public void postUpdate(SuperEntity superEntity) {
        log.info("Updated {}", superEntity);
    }
}
