package fr.tln.univ.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Date;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProduitNotFoundException.class)
    public ResponseEntity<ErrorMessage> productNotFound(ProduitNotFoundException pnf, WebRequest wr){
        ErrorMessage err = new ErrorMessage(LocalDateTime.now(),pnf.getMessage(),wr.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorMessage> clientNotFoundExceptionHandler(ClientNotFoundException cnfe, WebRequest wr){
        ErrorMessage err = new ErrorMessage(LocalDateTime.now(), cnfe.getMessage(), wr.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ErrorMessage> customerExceptionHandler(ClientException ce, WebRequest wr){
        ErrorMessage err = new ErrorMessage(LocalDateTime.now(), ce.getMessage(), wr.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ErrorMessage> loginExceptionHandler(LoginException le, WebRequest wr){
        ErrorMessage err = new ErrorMessage(LocalDateTime.now(), le.getMessage(), wr.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(CommandeException.class)
    public ResponseEntity<ErrorMessage> orderExceptionHandler(CommandeException oe, WebRequest wr){
        ErrorMessage err = new ErrorMessage(LocalDateTime.now(), oe.getMessage(), wr.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(Exception e, WebRequest wr){
        ErrorMessage err = new ErrorMessage(LocalDateTime.now(), e.getMessage(), wr.getDescription(false));
        return new ResponseEntity<ErrorMessage>(err, HttpStatus.BAD_REQUEST);
    }
/*    @ExceptionHandler(NotEditableException.class)
    public ResponseEntity<ErrorMessage> handelNotEditableException(NotEditableException ex, WebRequest request) {
        log.error("exception: "+ex);
        ex.printStackTrace();
        ErrorMessage message = new ErrorMessage(-20, new Date(), ex.getMessage(),
                "Error");
        return new ResponseEntity<>(message, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }*/
}
