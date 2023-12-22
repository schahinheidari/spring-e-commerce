package fr.tln.univ.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

/*    @ExceptionHandler(NotEditableException.class)
    public ResponseEntity<ErrorMessage> handelNotEditableException(NotEditableException ex, WebRequest request) {
        log.error("exception: "+ex);
        ex.printStackTrace();
        ErrorMessage message = new ErrorMessage(-20, new Date(), ex.getMessage(),
                "Error");
        return new ResponseEntity<>(message, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }*/
}
