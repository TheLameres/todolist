package thelameres.todolist.core.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not found Entity")
public class NotFoundException extends RuntimeException {
}
