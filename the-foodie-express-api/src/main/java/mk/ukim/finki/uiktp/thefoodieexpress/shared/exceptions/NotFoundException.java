package mk.ukim.finki.uiktp.thefoodieexpress.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundException extends ResponseStatusException {
    
    public NotFoundException() {
        super(HttpStatus.NOT_FOUND, "Resource could not be found");
    }
    
    public NotFoundException(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }
}
