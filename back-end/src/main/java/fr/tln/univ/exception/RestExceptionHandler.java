package fr.tln.univ.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class RestExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ApiException apiException) {
        return new ResponseEntity<>(apiException, apiException.getStatus());
    }

    @ExceptionHandler({NotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException exception) {
        ApiException apiException = new ApiException(NOT_FOUND
                , exception.getMessage(), LocalDateTime.now());
        return buildResponseEntity(apiException);
    }

    @ExceptionHandler({ConflictException.class})
    protected ResponseEntity<Object> handleConflictException(ConflictException exception) {
        ApiException apiException = new ApiException(CONFLICT
                , exception.getMessage(), LocalDateTime.now());
        return buildResponseEntity(apiException);
    }

    @ExceptionHandler({LoginException.class})
    protected ResponseEntity<Object> handleConflictException(LoginException loginException) {
        ApiException apiException = new ApiException(CONFLICT
                , loginException.getMessage(), LocalDateTime.now());
        return buildResponseEntity(apiException);
    }
}
