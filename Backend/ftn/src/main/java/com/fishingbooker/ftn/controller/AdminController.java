package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.reservations.Reservation;
import com.fishingbooker.ftn.bom.users.Administrator;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.*;
import com.fishingbooker.ftn.service.interfaces.AdministratorService;
import com.fishingbooker.ftn.service.interfaces.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
@PreAuthorize("hasRole('ADMINISTRATOR')")
public class AdminController {

    private final AdministratorService adminService;
    private final DataConverter converter;
    private final ReservationService reservationService;

    @GetMapping("/{id}")

    public Administrator get(@PathVariable("id") Long id) {
        Administrator administrator = adminService.findById(id);
        return administrator;
    }

    @PutMapping()

    public Administrator update(@RequestBody ApplicationUserDto adminDto) {
        Administrator admin = adminService.findById(adminDto.getId());
        Address address = admin.getUserAddress();
        address.setCity(adminDto.getCity());
        address.setStreet(adminDto.getStreet());
        address.setCountry(adminDto.getCountry());
        admin.setUserAddress(address);
        admin.setPassword(new BCryptPasswordEncoder().encode(adminDto.getPassword()));
        admin.setName(adminDto.getName());
        admin.setLastName(adminDto.getLastName());
        admin.setPhone(adminDto.getPhone());
        return adminService.save(admin);
    }

    @PostMapping()
    public Long addAdministrator(@RequestBody AdministratorDto administratorDto) {
        administratorDto.setRole("Administrator");
        Administrator administrator = converter.convert(administratorDto, Administrator.class);
        administrator.setFirstTimeLoggedIn(true);
        administrator.setEnabled(true);
        Administrator admin = adminService.save(administrator);
        return admin.getId();
    }

    @PutMapping("/change-password")
    public void changePassword(@RequestBody AdminChangePasswordDto adminDto) {
        adminService.changePassword(adminDto);
    }

    @GetMapping("/business-report")
    public List<AdminReservationStatisticsDto> getReservations() {
        List<Reservation> reservations = reservationService.getAll();
        List<AdminReservationStatisticsDto> reservationStatisticsDtos = converter.convert(reservations, AdminReservationStatisticsDto.class);
        return reservationStatisticsDtos;
    }

    @PutMapping("/business-report")
    public List<AdminReservationStatisticsDto> getReservationsInDate(@RequestBody DateRangeDto dateRangeDto) {
        List<Reservation> reservations = reservationService.getInDate(dateRangeDto.getStartDate(), dateRangeDto.getEndDate());
        List<AdminReservationStatisticsDto> reservationStatisticsDtos = converter.convert(reservations, AdminReservationStatisticsDto.class);
        return reservationStatisticsDtos;
    }
}
