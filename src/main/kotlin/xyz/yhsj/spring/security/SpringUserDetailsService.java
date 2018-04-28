package xyz.yhsj.spring.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpringUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        List<GrantedAuthority> roles = new ArrayList<>();
        if ("testuser1".equalsIgnoreCase(username)) {
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if ("testuser2".equalsIgnoreCase(username)) {
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            roles.add(new SimpleGrantedAuthority("ROLE_DBA"));
        } else {
            return null;
        }
        userDetails = new User(username, "password", roles);
        return userDetails;
    }
}
