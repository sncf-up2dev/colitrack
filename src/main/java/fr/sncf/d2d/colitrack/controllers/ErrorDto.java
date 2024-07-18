package fr.sncf.d2d.colitrack.controllers;

public record ErrorDto(
        String message
) {

    public static ErrorDto from(Exception exception) {
        return new ErrorDto(exception.getMessage());
    }
}
