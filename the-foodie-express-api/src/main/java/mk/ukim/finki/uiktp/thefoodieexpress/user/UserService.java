package mk.ukim.finki.uiktp.thefoodieexpress.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.Authentication;

public interface UserService {

    AuthResponse register(RegisterDto registerDto) throws JsonProcessingException;

    AuthResponse login(LoginDto loginDto) throws JsonProcessingException;

    User update(RegisterDto registerDto, Authentication authentication);
}
