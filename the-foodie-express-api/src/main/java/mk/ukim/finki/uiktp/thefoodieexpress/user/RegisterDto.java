package mk.ukim.finki.uiktp.thefoodieexpress.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class RegisterDto {

    @NotBlank(message = "The email cannot be null or whitespace")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "The name cannot be null or whitespace")
    @Size(min = 1, max = 255, message = "The name must be between 1 and 255 characters")
    private String name;

    @NotBlank(message = "The surname cannot be null or whitespace")
    @Size(min = 1, max = 255, message = "The surname must be between 1 and 255 characters")
    private String surname;

    @Pattern(regexp = "(USER)|(ADMIN)", message = "The role must be one of the following: [USER, ADMIN]")
    private String role;

    @NotBlank(message = "The password cannot be null or whitespace")
    @Size(min = 8, max = 16, message = "The password must be between 8 and 16 characters")
    private String password;
}
