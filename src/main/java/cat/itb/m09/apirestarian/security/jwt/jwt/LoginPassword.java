package cat.itb.m09.apirestarian.security.jwt.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoginPassword {

    private String username;
    private String password;
}