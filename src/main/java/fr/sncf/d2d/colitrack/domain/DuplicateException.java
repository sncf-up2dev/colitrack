package fr.sncf.d2d.colitrack.domain;

public class DuplicateException extends DomainException {

    public DuplicateException(String message) {
        super(message);
    }
}
