package fr.tln.univ.exception;

public class CommandeException extends RuntimeException{
    public CommandeException() {

    }
    public CommandeException(String message) {
        super(message);
    }

}
