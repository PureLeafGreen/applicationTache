package ca.christopher.applicationtache.exceptions;

import org.springframework.http.HttpStatusCode;

public class EmailAlreadyExistsException extends RuntimeException {

    private final HttpStatusCode code;
    public EmailAlreadyExistsException(String message, HttpStatusCode code) {
        super(message);
        this.code = code;
    }

    public EmailAlreadyExistsException() {
        super("Email already exists");
        this.code = HttpStatusCode.valueOf(400);
    }
}
