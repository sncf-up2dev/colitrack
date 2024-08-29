package fr.sncf.d2d.colitrack.controllers;

import fr.sncf.d2d.colitrack.domain.DuplicateException;
import fr.sncf.d2d.colitrack.domain.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handle(NotFoundException exception) {
        return ErrorDto.from(exception);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDto handle(DuplicateException exception) {
        return ErrorDto.from(exception);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handle(MethodArgumentNotValidException ex) {
        if (ex.getBindingResult().getAllErrors().isEmpty()) {
            return ErrorDto.from(ex);
        }
        String errors = ex.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    String name = (error instanceof FieldError fieldError) ?
                            fieldError.getField() :
                            error.getObjectName();
                    return "In [%s]: %s".formatted(name, error.getDefaultMessage());
                })
                .collect(Collectors.joining(", "));
        return new ErrorDto(errors);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> hande(ResponseStatusException exception) {
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(ErrorDto.from(exception));
    }
}
