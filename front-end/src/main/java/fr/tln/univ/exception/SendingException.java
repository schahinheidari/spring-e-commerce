package fr.tln.univ.exception;

public class SendingException extends RuntimeException{

    public SendingException() {
    }

    public SendingException(String message) {
        super(message);
    }
}
