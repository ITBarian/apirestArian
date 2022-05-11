package cat.itb.m09.apirestarian.security.jwt.jwt;

import cat.itb.m09.apirestarian.model.entities.UsersDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserJwt extends UsersDTO {
    private String token;

    @Builder(builderMethodName = "jwtUsuariJwtBuilder")
    public UserJwt(String username, String rol, String token) {
        super(username, rol);
        this.token = token;
    }
}