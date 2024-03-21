package core.security.chapter03;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
public class User {

    @Id
    private Long id;
    private String username;
    private String password;
    private String authority;

}
