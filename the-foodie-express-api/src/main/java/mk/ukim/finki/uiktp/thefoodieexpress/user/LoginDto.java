package mk.ukim.finki.uiktp.thefoodieexpress.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class LoginDto {

    @NotBlank(message = "The email cannot be null or whitespace")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "The password cannot be null or whitespace")
    @Size(min = 8, max = 16, message = "The password must be between 8 and 16 characters")
    private String password;
}
