package ca.christopher.applicationtache.config;

import ca.christopher.applicationtache.exceptions.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = {AppException.class})
    @ResponseBody
    public ResponseEntity<Object> handleAppException(AppException e) {
        return ResponseEntity.status(e.getCode()).body(e.getMessage());
    }
}
