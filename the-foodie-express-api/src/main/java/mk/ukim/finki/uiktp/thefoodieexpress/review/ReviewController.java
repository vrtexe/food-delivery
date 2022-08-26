package mk.ukim.finki.uiktp.thefoodieexpress.review;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/new")
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto, Authentication authentication) {
        var result = ReviewDto.fromEntity(reviewService.submit(reviewDto, authentication));

        return ResponseEntity.ok(result);
    }
}
