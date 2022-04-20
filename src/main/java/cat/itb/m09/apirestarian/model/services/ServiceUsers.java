package cat.itb.m09.apirestarian.model.services;

import cat.itb.m09.apirestarian.model.entities.Users;
import cat.itb.m09.apirestarian.model.repositories.RepoUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceUsers {

    private final RepoUsers repoUsers;

    private final PasswordEncoder xifrat;

    public Users findByUser(String username) {
        return repoUsers.findByUsername(username).orElse(null);
    }

    public Users createNewUser(Users newUser) {
        //falta controlar que els 2 passwords del client coincideixen
        //passar un UsuariCreacioDTO
        newUser.setPassword(xifrat.encode(newUser.getPassword()));
        repoUsers.save(newUser);
        return newUser;
    }

    public List<Users> listAllUsers(){
        return repoUsers.findAll();
    }


}
