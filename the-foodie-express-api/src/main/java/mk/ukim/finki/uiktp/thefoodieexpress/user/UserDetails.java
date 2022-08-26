package mk.ukim.finki.uiktp.thefoodieexpress.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDetails {

    private String email;

    private Role role;

    public UserDetails(User user) {
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
