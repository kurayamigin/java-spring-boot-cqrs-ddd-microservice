package my.microservice.videogames.cross_cutting.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

public abstract class RestException extends ResponseStatusException {
    public ResponseEntity<?> response;

    public RestException(String format) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, format);
        this.response = ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(format);
    }

    public RestException(HttpStatus status, String format) {
        super(status, format);
        this.response = ResponseEntity
                .status(status)
                .body(format);
    }

    public RestException(HttpStatus status) {
        super(status);
        this.response = ResponseEntity
                .status(status)
                .body(super.getMessage());
    }
    public RestException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR);
        this.response = ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(super.getMessage());
    }

    public RestException(String message, ResponseEntity<?> response) {
        super(response.getStatusCode(), message);
        this.response = response;
    }

    public ResponseEntity<?> getResponse() {
        return response;
    }
}
