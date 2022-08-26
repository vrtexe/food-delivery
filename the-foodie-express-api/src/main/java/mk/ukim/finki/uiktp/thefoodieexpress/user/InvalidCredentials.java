package mk.ukim.finki.uiktp.thefoodieexpress.user;

import mk.ukim.finki.uiktp.thefoodieexpress.shared.exceptions.BadRequestException;
import mk.ukim.finki.uiktp.thefoodieexpress.shared.exceptions.InternalServerException;

public class InvalidCredentials extends InternalServerException {
    public InvalidCredentials() {
        super("The provided credentials are invalid");
    }
}
