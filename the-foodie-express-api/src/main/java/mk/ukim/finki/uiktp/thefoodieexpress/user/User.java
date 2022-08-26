package mk.ukim.finki.uiktp.thefoodieexpress.user;

import lombok.Data;
import mk.ukim.finki.uiktp.thefoodieexpress.restaurant.Restaurant;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "app_user")
public class User {

    @Id
    private String email;

    private String name;

    private String surname;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    private String password;

    @ManyToMany
    private Set<Restaurant> favouriteRestaurants;

    public User() {
        this.favouriteRestaurants = new HashSet<>();
    }

    public User(RegisterDto registerDto) {
        this.email = registerDto.getEmail();
        this.name = registerDto.getName();
        this.surname = registerDto.getSurname();
        this.role = Role.valueOf(registerDto.getRole());
        this.password = registerDto.getPassword();
    }

    public void update(RegisterDto registerDto) {
        this.email = registerDto.getEmail();
        this.name = registerDto.getName();
        this.surname = registerDto.getSurname();
        this.role = Role.valueOf(registerDto.getRole());
        this.password = registerDto.getPassword();
    }
}
