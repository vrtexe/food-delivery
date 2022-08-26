package mk.ukim.finki.uiktp.thefoodieexpress.user;

import mk.ukim.finki.uiktp.thefoodieexpress.shared.exceptions.NotFoundException;

public class UserNotFound extends NotFoundException {
    public UserNotFound(String email) {
        super("The user with the provided email [%s] was not found".formatted(email));
    }
}
