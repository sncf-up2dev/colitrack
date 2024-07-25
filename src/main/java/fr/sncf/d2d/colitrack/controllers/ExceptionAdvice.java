package fr.sncf.d2d.colitrack.controllers;

import fr.sncf.d2d.colitrack.domain.DuplicateException;
import fr.sncf.d2d.colitrack.domain.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    public ErrorDto handle(BadRequestException exception) {
        return ErrorDto.from(exception);
    }
}
