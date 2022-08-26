package mk.ukim.finki.uiktp.thefoodieexpress.restaurant;

import mk.ukim.finki.uiktp.thefoodieexpress.shared.exceptions.NotFoundException;

public class RestaurantNotFound extends NotFoundException {
    public RestaurantNotFound(String id) {
        super("The restaurant with the provided id [%s] was not found".formatted(id));
    }
}
