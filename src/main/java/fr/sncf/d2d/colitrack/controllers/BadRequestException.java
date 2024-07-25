package fr.sncf.d2d.colitrack.controllers;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
