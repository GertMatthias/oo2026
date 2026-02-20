package ee.eljas.autonimekiri.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.Date;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(IllegalArgumentException e) {

        ErrorMessage viga = new ErrorMessage(new Date(), e.getMessage(), 400);
        return new ResponseEntity<>(viga, HttpStatus.BAD_REQUEST);

    }
}
