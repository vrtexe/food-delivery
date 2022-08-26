package mk.ukim.finki.uiktp.thefoodieexpress.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import mk.ukim.finki.uiktp.thefoodieexpress.config.JwtAuthConstants;
import mk.ukim.finki.uiktp.thefoodieexpress.shared.InvalidInput;
import mk.ukim.finki.uiktp.thefoodieexpress.shopping_cart.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Validator validator;
    private final PasswordEncoder encoder;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartItemRepository shoppingCartItemRepository;

    @Override
    public AuthResponse register(RegisterDto registerDto) throws InvalidInput, UserAlreadyExists, JsonProcessingException {
        this.validateRegisterData(registerDto);

        String password = registerDto.getPassword();

        password = this.encoder.encode(password);
        registerDto.setPassword(password);

        User user = this.userRepository.save(new User(registerDto));
        String jwt = this.generateToken(user);

        ShoppingCart shoppingCart = this.shoppingCartRepository.save(new ShoppingCart(user));

        return new AuthResponse(user, jwt, shoppingCart.getId(), new ArrayList<>());
    }

    @Override
    public AuthResponse login(LoginDto loginDto) throws InvalidInput, UserNotFound, InvalidCredentials, JsonProcessingException, ShoppingCartNotFound {
        User user = this.validateLoginData(loginDto);

        String jwt = this.generateToken(user);

        ShoppingCart shoppingCart = this.findShoppingCartByUser(user);

        List<ShoppingCartItemDetails> shoppingCartItems =
                this.shoppingCartItemRepository.findAllByShoppingCart(shoppingCart)
                        .stream()
                        .map(ShoppingCartItemDetails::new)
                        .collect(Collectors.toList());

        return new AuthResponse(user, jwt, shoppingCart.getId(), shoppingCartItems);
    }

    @Override
    public User update(RegisterDto registerDto, Authentication authentication) throws InvalidInput, UserAlreadyExists, UserNotFound {
        User user = this.validateProfileUpdateData(registerDto, authentication);
        String password = registerDto.getPassword();

        password = this.encoder.encode(password);
        registerDto.setPassword(password);

        user.update(registerDto);
        user = this.userRepository.save(user);

        return user;
    }

    private void validateRegisterData(RegisterDto registerDto) {
        List<ConstraintViolation<RegisterDto>> constraintViolations = new ArrayList<>(this.validator.validate(registerDto));

        if (constraintViolations.size() > 0) {
            throw new InvalidInput(constraintViolations.get(0).getMessage());
        }

        String email = registerDto.getEmail();
        boolean isRegistered = this.userRepository.existsById(email);

        if (isRegistered) {
            throw new UserAlreadyExists(email);
        }
    }

    private User validateLoginData(LoginDto loginDto) throws UserNotFound {
        List<ConstraintViolation<LoginDto>> constraintViolations = new ArrayList<>(this.validator.validate(loginDto));

        if (constraintViolations.size() > 0) {
            throw new InvalidInput(constraintViolations.get(0).getMessage());
        }

        String email = loginDto.getEmail();
        User user = this.findByEmail(email);

        if (!this.encoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new InvalidCredentials();
        }

        return user;
    }

    private User validateProfileUpdateData(RegisterDto registerDto, Authentication authentication) {
        List<ConstraintViolation<RegisterDto>> constraintViolations = new ArrayList<>(this.validator.validate(registerDto));

        if (constraintViolations.size() > 0) {
            throw new InvalidInput(constraintViolations.get(0).getMessage());
        }

        String email = (String) authentication.getPrincipal();

        if (!email.equals(registerDto.getEmail())) {
            boolean isRegistered = this.userRepository.existsById(registerDto.getEmail());

            if (isRegistered) {
                throw new UserAlreadyExists(email);
            }
        }

        return this.findByEmail(email);
    }

    private User findByEmail(String email) {
        return this.userRepository.findById(email)
                .orElseThrow(() -> new UserNotFound(email));
    }

    private String generateToken(User user) throws JsonProcessingException {
        return JWT.create()
                .withSubject(new ObjectMapper().writeValueAsString(new UserDetails(user)))
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtAuthConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(JwtAuthConstants.SECRET.getBytes()));
    }

    private ShoppingCart findShoppingCartByUser(User user) {
        return this.shoppingCartRepository.findByUser(user)
                .orElseThrow(() -> new ShoppingCartNotFound(user.getEmail()));
    }
}
