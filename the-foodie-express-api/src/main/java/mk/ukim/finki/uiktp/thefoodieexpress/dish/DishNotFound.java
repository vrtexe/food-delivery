package mk.ukim.finki.uiktp.thefoodieexpress.dish;

import mk.ukim.finki.uiktp.thefoodieexpress.shared.exceptions.NotFoundException;

public class DishNotFound extends NotFoundException {
    public DishNotFound(String id) {
        super("The dish with the provided id [%s] was not found".formatted(id));
    }
}
