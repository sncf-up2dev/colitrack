package fr.sncf.d2d.colitrack.domain;

public class DuplicateException extends RuntimeException {

    public DuplicateException(String message) {
        super(message);
    }
}
