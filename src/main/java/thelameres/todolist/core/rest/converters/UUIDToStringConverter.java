package thelameres.todolist.core.rest.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Component
public class UUIDToStringConverter implements Converter<String, UUID> {
    @Override
    public UUID convert(@NotNull String s) {
        return UUID.fromString(s);
    }
}
