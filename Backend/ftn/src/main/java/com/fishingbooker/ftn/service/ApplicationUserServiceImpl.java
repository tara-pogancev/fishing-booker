package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.RegistrationRequest;
import com.fishingbooker.ftn.bom.users.ApplicationRole;
import com.fishingbooker.ftn.bom.users.ApplicationUser;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import com.fishingbooker.ftn.email.context.AccountVerificationEmailContext;
import com.fishingbooker.ftn.email.service.EmailService;
import com.fishingbooker.ftn.repository.ApplicationUserRepository;
import com.fishingbooker.ftn.repository.RegistrationRequestRepository;
import com.fishingbooker.ftn.repository.RegistrationTokenRepository;
import com.fishingbooker.ftn.security.registration.RegistrationToken;
import com.fishingbooker.ftn.security.registration.RegistrationTokenService;
import com.fishingbooker.ftn.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final DataConverter converter;
    private final EmailService emailService;
    private final AddressService addressService;
    private final BoatOwnerService boatOwnerService;
    private final RegistrationTokenService tokenService;
    private final RegisteredClientService clientService;
    private final CottageOwnerService cottageOwnerService;
    private final ApplicationUserRepository userRepository;
    private final AdministratorService administratorService;
    private final FishingInstructorService fishingInstructorService;
    private final RegistrationTokenRepository registrationTokenRepository;
    private final RegistrationRequestRepository registrationRequestRepository;
    @Value("${site.base.url.https}")
    private String baseURL;

    @Override
    public List<ApplicationUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public ApplicationUser findById(Long id) {
        return userRepository.get(id);
    }

    @Override
    public ApplicationUser findByEmail(String email) {
        for (ApplicationUser currentUser : findAll()) {
            if (currentUser.getEmail().equals(email))
                return currentUser;
        }
        return null;
    }

    @Override
    public ApplicationUser create(ApplicationUserDto userDto) {
        if (findByEmail(userDto.getEmail()) == null) {
            ApplicationUser user = converter.convert(userDto, ApplicationUser.class);
            switch (user.getRole()) {
                case ADMINISTRATOR:
                    user = administratorService.create(userDto);
                    break;
                case BOAT_OWNER:
                    user = boatOwnerService.create(userDto);
                    break;
                case COTTAGE_OWNER:
                    user = cottageOwnerService.create(userDto);
                    break;
                case FISHING_INSTRUCTOR:
                    user = fishingInstructorService.create(userDto);
                    break;
                default:
                    user = clientService.create(userDto);
                    break;
            }
            sendRegistrationConfirmationEmail(user);

            Address userAddress = addressService.create(userDto);
            user.setUserAddress(userAddress);
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            update(user);
            System.out.println("User added!");
            return user;
        }
        return null;
    }

    @Override
    public ApplicationUser update(ApplicationUser user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Boolean verifyUser(String token) throws Exception {
        RegistrationToken secureToken = tokenService.findByToken(token);
        if (Objects.isNull(secureToken) || !StringUtils.equals(token, secureToken.getToken()) || secureToken.isExpired()) {
            throw new Exception("Invalid token!");
        }
        ApplicationUser user = userRepository.get(secureToken.getUser().getId());
        if (Objects.isNull(user)) {
            System.out.println("ERROR: No user found!");
            return false;
        }

        System.out.println("Found user to verify: " + user.getName());
        user.setEnabled(true);
        userRepository.save(user);

        tokenService.removeToken(secureToken);
        return true;
    }

    @Override
    public ApplicationUser createWithRequest(ApplicationUserDto userDto) {
        ApplicationUser user=new ApplicationUser();
        RegistrationRequest request=new RegistrationRequest();
        request.setRegistrationDescription(userDto.getRegistrationDescription());
        if (ApplicationRole.getRoleFromString(userDto.getRole())==ApplicationRole.FISHING_INSTRUCTOR)
            user = fishingInstructorService.create(userDto);
        else if(ApplicationRole.getRoleFromString(userDto.getRole())==ApplicationRole.BOAT_OWNER){
            user = boatOwnerService.create(userDto);
        }// todo add for other types of users

        request.setUser(user);
        registrationRequestRepository.save(request);
        return user;
    }

    public void sendRegistrationConfirmationEmail(ApplicationUser user) {
        RegistrationToken secureToken = tokenService.createSecureToken();
        secureToken.setUser(user);
        registrationTokenRepository.save(secureToken);
        AccountVerificationEmailContext emailContext = new AccountVerificationEmailContext();
        emailContext.init(user);
        emailContext.setToken(secureToken.getToken());
        emailContext.buildVerificationUrl(baseURL, secureToken.getToken());
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
