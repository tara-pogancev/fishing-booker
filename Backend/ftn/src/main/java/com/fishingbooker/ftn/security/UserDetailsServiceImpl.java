package com.fishingbooker.ftn.security;

import com.fishingbooker.ftn.bom.users.ApplicationRole;
import com.fishingbooker.ftn.bom.users.ApplicationUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String ROLE_PREFIX = "ROLE_";
    //private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // todo: jwt
        //var user = userRepository.get(username);
        ApplicationUser user = null;
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(username, user.getPassword(), getRole(user.getRole()));
    }

    private List<SimpleGrantedAuthority> getRole(ApplicationRole role) {
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        String roleName = ROLE_PREFIX + role;
        grantedAuthorities.add(new SimpleGrantedAuthority(roleName));
        return grantedAuthorities;
    }

}

