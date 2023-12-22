package fr.tln.univ.exception;

public class ClientNotFoundException extends RuntimeException{

    public ClientNotFoundException() {
        super();
    }

    public ClientNotFoundException(String message) {
        super(message);
    }

}
