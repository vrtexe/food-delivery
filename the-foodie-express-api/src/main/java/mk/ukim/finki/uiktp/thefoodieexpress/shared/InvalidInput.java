package mk.ukim.finki.uiktp.thefoodieexpress.shared;

import mk.ukim.finki.uiktp.thefoodieexpress.shared.exceptions.BadRequestException;

public class InvalidInput extends BadRequestException {
    public InvalidInput(String message) {
        super(message);
    }
}
