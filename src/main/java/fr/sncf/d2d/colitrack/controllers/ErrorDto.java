package fr.sncf.d2d.colitrack.controllers;

import org.springframework.web.server.ResponseStatusException;

public record ErrorDto(
        String message
) {

    public static ErrorDto from(Exception exception) {
        return new ErrorDto(exception.getMessage());
    }

    public static ErrorDto from(ResponseStatusException exception) {
        return new ErrorDto(exception.getReason());
    }
}
