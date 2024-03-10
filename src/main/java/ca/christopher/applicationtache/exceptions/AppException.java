package ca.christopher.applicationtache.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatusCode;
@EqualsAndHashCode(callSuper = true)
@Data
public class AppException extends RuntimeException {

    private final HttpStatusCode code;

    public AppException(String message, HttpStatusCode code) {
        super(message);
        this.code = code;
    }

    public AppException() {
        super("App exception");
        this.code = HttpStatusCode.valueOf(400);
    }
}
