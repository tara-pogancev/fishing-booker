package com.fishingbooker.ftn.controller;


import com.fishingbooker.ftn.bom.users.ApplicationUser;
import com.fishingbooker.ftn.security.JwtUtil;
import com.fishingbooker.ftn.security.model.AuthenticationRequest;
import com.fishingbooker.ftn.security.model.AuthenticationResponse;
import com.fishingbooker.ftn.service.interfaces.ApplicationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final ApplicationUserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword());
            authenticationManager.authenticate(token);
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect email or password.", e);
        }

        ApplicationUser user = userService.findByEmail(authenticationRequest.getEmail());
        if (!user.getEnabled()) {
            throw new Exception("Account is not verified. Please check your email or contact the administrator.");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt, user.getName(), user.getRole().toString(), user.getEmail()));
    }

}
