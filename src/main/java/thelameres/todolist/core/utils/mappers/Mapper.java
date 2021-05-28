package thelameres.todolist.core.utils.mappers;

import thelameres.todolist.core.data.dto.SuperDto;
import thelameres.todolist.core.data.models.SuperEntity;

public interface Mapper<E extends SuperEntity, D extends SuperDto> {
    E toEntity(D dto);
    D toDto(E entity);
}
