package mk.ukim.finki.uiktp.thefoodieexpress.review;

import org.springframework.security.core.Authentication;

public interface ReviewService {

    Review submit(ReviewDto reviewDto, Authentication authentication);
}
