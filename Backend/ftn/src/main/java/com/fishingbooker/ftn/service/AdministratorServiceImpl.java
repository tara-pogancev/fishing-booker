package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.users.Administrator;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.AdminChangePasswordDto;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import com.fishingbooker.ftn.repository.AdministratorRepository;
import com.fishingbooker.ftn.service.interfaces.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdministratorServiceImpl implements AdministratorService {

    private final DataConverter converter;
    private final AdministratorRepository administratorRepository;

    @Override
    public List<Administrator> findAll() {
        return administratorRepository.findAll();
    }

    @Override
    public Administrator findById(Long id) {
        return administratorRepository.get(id);
    }

    @Override
    public Administrator create(ApplicationUserDto userDto) {
        Administrator administrator = converter.convert(userDto, Administrator.class);
        return administratorRepository.save(administrator);
    }

    @Override
    public Administrator save(Administrator admin) {
        admin.setEnabled(true);
        admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
        return administratorRepository.save(admin);
    }

    @Override
    public void changePassword(AdminChangePasswordDto adminDto) {
        Administrator admin=administratorRepository.get(adminDto.getId());
        admin.setPassword(new BCryptPasswordEncoder().encode(adminDto.getPassword()));
        admin.setFirstTimeLoggedIn(false);
        administratorRepository.save(admin);
    }

}
