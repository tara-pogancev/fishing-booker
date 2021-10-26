package com.fishingbooker.ftn.security;

import com.fishingbooker.ftn.security.model.AuthenticationRequest;
import com.fishingbooker.ftn.security.model.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    //private final UserService userService;
    //private final DataConverter conversionService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            authenticationManager.authenticate(token);
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password.", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping("/identify/{username}")
    public Boolean identify(@PathVariable String username, @RequestBody List<String> rolesRequired) {
        // todo: jwt
        //UserModel user = userService.get(username);
        Boolean validation = false;
        /*if (user != null) {
                if (rolesRequired.contains(user.getRole)) {
                    validation = true;
                }
        }*/
        return validation;
    }

}