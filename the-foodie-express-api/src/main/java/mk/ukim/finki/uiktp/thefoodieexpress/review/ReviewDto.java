package mk.ukim.finki.uiktp.thefoodieexpress.review;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Builder
@Data
public class ReviewDto {
    
    @NotBlank(message = "The restaurant's id cannot be null or whitespace")
    @Size(min = 1, max = 255, message = "The restaurant's id must be between 1 and 255 characters")
    private String restaurantId;
    
    @NotNull(message = "The score cannot be null")
    @Min(value = 1, message = "The score should not be less than 1")
    @Max(value = 5, message = "The score should not be greater than 5")
    private Integer score;
    
    public static ReviewDto fromEntity(Review review) {
        var reviewDto = new ReviewDtoBuilder();
        
        reviewDto.restaurantId(review.getId())
                .score(review.getScore());
        
        return reviewDto.build();
    }
}
