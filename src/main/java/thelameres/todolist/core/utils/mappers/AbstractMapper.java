package thelameres.todolist.core.utils.mappers;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import thelameres.todolist.core.data.dto.SuperDto;
import thelameres.todolist.core.data.models.SuperEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class AbstractMapper<E extends SuperEntity, D extends SuperDto> implements Mapper<E, D> {

    @Autowired
    ModelMapper mapper;

    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    AbstractMapper(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public E toEntity(D dto) {
        return Objects.isNull(dto)
                ? null
                : mapper.map(dto, entityClass);
    }

    @Override
    public D toDto(E entity) {
        return Objects.isNull(entity)
                ? null
                : mapper.map(entity, dtoClass);
    }

    Converter<E, D> toDtoConverter() {
        return context -> {
            E source = context.getSource();
            D destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    Converter<D, E> toEntityConverter() {
        return context -> {
            D source = context.getSource();
            E destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    void mapSpecificFields(E source, D destination) {
        destination.setCreatedAt(source.getCreatedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        destination.setUpdatedAt(source.getUpdatedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    }

    void mapSpecificFields(D source, E destination) {
        destination.setCreatedAt(LocalDateTime.parse(source.getCreatedAt(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        destination.setUpdatedAt(LocalDateTime.parse(source.getUpdatedAt(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    }
}
