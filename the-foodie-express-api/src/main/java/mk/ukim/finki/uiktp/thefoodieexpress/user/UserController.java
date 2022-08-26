package mk.ukim.finki.uiktp.thefoodieexpress.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import mk.ukim.finki.uiktp.thefoodieexpress.shared.exceptions.BadRequestException;
import mk.ukim.finki.uiktp.thefoodieexpress.shared.exceptions.InternalServerException;
import mk.ukim.finki.uiktp.thefoodieexpress.shared.exceptions.NotFoundException;
import mk.ukim.finki.uiktp.thefoodieexpress.shopping_cart.ShoppingCartNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterDto registerDto) {
        try {
            return ResponseEntity.ok(this.userService.register(registerDto));
        } catch (JsonProcessingException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDto loginDto) {
        try {
            return ResponseEntity.ok(this.userService.login(loginDto));
        } catch (ShoppingCartNotFound e) {
            throw new NotFoundException(e.getMessage());
        } catch (JsonProcessingException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @PutMapping("/profile-update")
    public ResponseEntity<User> updateUser(@RequestBody RegisterDto registerDto, Authentication authentication) {
        return ResponseEntity.ok(this.userService.update(registerDto, authentication));
    }
}
