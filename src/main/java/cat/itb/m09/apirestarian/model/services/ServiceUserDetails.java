package cat.itb.m09.apirestarian.model.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceUserDetails implements UserDetailsService {

    private final ServiceUsers serviceUsersDetails;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return serviceUsersDetails.findByUser(username);
    }

    public UserDetails loadUserById(Long id){
        return serviceUsersDetails.consultarPerId(id);
    }
}