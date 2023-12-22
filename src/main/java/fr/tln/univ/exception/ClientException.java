package fr.tln.univ.exception;

public class ClientException extends RuntimeException{
    public ClientException() {
        super();
    }

    public ClientException(String message) {
        super(message);
    }
}

