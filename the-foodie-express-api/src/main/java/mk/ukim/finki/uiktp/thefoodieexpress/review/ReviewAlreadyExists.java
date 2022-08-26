package mk.ukim.finki.uiktp.thefoodieexpress.review;

import mk.ukim.finki.uiktp.thefoodieexpress.shared.exceptions.NotFoundException;

public class ReviewAlreadyExists extends NotFoundException {
    public ReviewAlreadyExists(String name) {
        super("A review for the provided restaurant [%s] already exists".formatted(name));
    }
}
