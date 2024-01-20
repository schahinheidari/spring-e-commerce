package fr.tln.univ.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String exception) {
        super(exception);
    }
}