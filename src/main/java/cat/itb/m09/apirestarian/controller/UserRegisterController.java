package cat.itb.m09.apirestarian.controller;

import cat.itb.m09.apirestarian.model.entities.Users;
import cat.itb.m09.apirestarian.model.entities.UsersDTO;
import cat.itb.m09.apirestarian.model.services.ServiceUsers;
import cat.itb.m09.apirestarian.security.jwt.jwt.JwtProvider;
import cat.itb.m09.apirestarian.security.jwt.jwt.LoginPassword;
import cat.itb.m09.apirestarian.security.jwt.jwt.UserJwt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserRegisterController {
    private final ServiceUsers serveiUsuaris;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<UserJwt> login(@RequestBody LoginPassword userPassword)
    {
        Authentication auth=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userPassword.getUsername(),userPassword.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        Users usu=(Users) auth.getPrincipal();
        String jwtToken = tokenProvider.generateToken(auth);
        UserJwt usu2=new UserJwt(usu.getUsername(),usu.getRol(),jwtToken);
        //es retorna userName, Avatar, Rol i Token
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usu2);
    }


    @GetMapping("/login")
    public UsersDTO login(@AuthenticationPrincipal Users usu){
        UsersDTO usu2=new UsersDTO(usu.getUsername(),usu.getRol());
        return usu2;
    }



}