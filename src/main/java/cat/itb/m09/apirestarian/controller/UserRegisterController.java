package cat.itb.m09.apirestarian.controller;

import cat.itb.m09.apirestarian.model.entities.Users;
import cat.itb.m09.apirestarian.model.entities.UsersDTO;
import cat.itb.m09.apirestarian.model.services.ServiceUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserRegisterController {
    private final ServiceUsers serviceUsers;


    @GetMapping("/me")
    public UsersDTO findWho(@AuthenticationPrincipal Users user) {
        return new UsersDTO(user.getUsername(), user.getRol());
    }

    /*
    {
    "username":"Montse",
    "password":"1234",
    "avatar":"http://imatge.com"
    }
    Afegeix id automàticament
     */
    @PostMapping("/users")
    public ResponseEntity<?> newUser(@RequestBody Users newUser) {
        try {
            Users createUser = serviceUsers.createNewUser(newUser);
            UsersDTO usersDTO = new UsersDTO(createUser.getUsername(), createUser.getRol());
            return new ResponseEntity<UsersDTO>(usersDTO, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            //per si intentem afegir 2 usuaris amb el mateix username, saltarà excepció
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> listUsersDTO() {
        List<Users> allUsers = serviceUsers.listAllUsers();
        List<UsersDTO> blankList = new ArrayList();
        for (Users user : allUsers) {
            blankList.add(new UsersDTO(user.getUsername(), user.getRol()));
        }
        if (allUsers.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else return ResponseEntity.ok(blankList);
    }
}