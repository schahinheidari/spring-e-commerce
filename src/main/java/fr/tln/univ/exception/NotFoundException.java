package fr.tln.univ.exception;


/**
 * Defines an exception to be thrown when an entity is not found.
 *
 * @author dnardelli
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String entity) {
        super(entity + " not found");
    }

}
