package mk.ukim.finki.uiktp.thefoodieexpress.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InternalServerException extends ResponseStatusException {
    
    public InternalServerException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    public InternalServerException(String reason) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, reason);
    }
}
