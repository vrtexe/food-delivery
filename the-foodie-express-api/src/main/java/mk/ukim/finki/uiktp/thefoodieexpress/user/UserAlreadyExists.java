package mk.ukim.finki.uiktp.thefoodieexpress.user;

import mk.ukim.finki.uiktp.thefoodieexpress.shared.exceptions.BadRequestException;

public class UserAlreadyExists extends BadRequestException {
    public UserAlreadyExists(String email) {
        super(String.format("A user with the provided email [%s] already exists", email));
    }
}
