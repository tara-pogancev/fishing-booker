package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.users.ApplicationUser;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import com.fishingbooker.ftn.service.interfaces.ApplicationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final ApplicationUserService userService;
    private final MessageSource messageSource;

    @PostMapping
    public ApplicationUser userRegistration(@RequestBody ApplicationUserDto userDto) {
        return userService.create(userDto);
    }

    @GetMapping("/isEmailUnique/{emailAddress}")
    public Boolean isEmailUnique(@PathVariable String emailAddress) {
        return (userService.findByEmail(emailAddress) == null);
    }

    @GetMapping("/verify")
    public String verifyCustomer(@RequestParam(required = false) String token) throws Exception {
        if (StringUtils.isEmpty(token)) {
            return "No token provided!";
        }
        try {
            userService.verifyUser(token);
        } catch (Exception e) {
            return "An unexpected error occurred.";
        }

        return "Thank you for registering. Feel free to go back to our website and login.";
    }

}
