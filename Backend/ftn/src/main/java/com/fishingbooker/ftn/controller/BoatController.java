package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.BoatDto;
import com.fishingbooker.ftn.dto.EntitySearchDto;
import com.fishingbooker.ftn.dto.ReservationDto;
import com.fishingbooker.ftn.service.interfaces.BoatService;
import com.fishingbooker.ftn.service.interfaces.RegisteredClientService;
import com.fishingbooker.ftn.service.interfaces.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/boats")
public class BoatController {

    private final BoatService boatService;
    private final DataConverter converter;
    private final ReservationService reservationService;
    private final RegisteredClientService clientService;

    @GetMapping()
    public List<BoatDto> get() {
        List<Boat> boats = boatService.findAll();
        return converter.convert(boats, BoatDto.class);
    }

    // Post because of request body
    @PostMapping("/search/{userId}")
    @PreAuthorize("hasRole('REGISTERED_CLIENT')")
    public ResponseEntity<List<BoatDto>> getSearch(@RequestBody EntitySearchDto filterDto, @PathVariable Long userId) {
        if (clientService.clientHasOverlappingReservation(filterDto.startDate, filterDto.endDate, userId)) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } else {
            List<Boat> boats = boatService.findFiltered(filterDto, userId);
            List<BoatDto> dtoRetVal = converter.convert(boats, BoatDto.class);
            return new ResponseEntity<>(dtoRetVal, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('BOAT_OWNER')")
    public Boolean delete(@PathVariable("id") Long id) {
        return boatService.delete(id);
    }

    @PostMapping("/book")
    public ResponseEntity<Long> book(@RequestBody ReservationDto reservationDto) {
        BoatReservation reservation = reservationService.bookBoat(reservationDto);
        if (reservation == null) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(reservation.getId(), HttpStatus.OK);
        }
    }

    @GetMapping("/findByBoatOwnerId/{id}")
    @PreAuthorize("hasRole('BOAT_OWNER')")
    public List<BoatDto> getByBoatOwnerId(@PathVariable("id") long id) {
        return boatService.findByBoatOwnerId(id);
    }

}
