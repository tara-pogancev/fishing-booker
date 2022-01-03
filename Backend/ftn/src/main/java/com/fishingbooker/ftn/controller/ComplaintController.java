package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.complaints.Complaint;
import com.fishingbooker.ftn.bom.reservations.Reservation;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.ComplaintDto;
import com.fishingbooker.ftn.dto.ComplaintResponseDto;
import com.fishingbooker.ftn.dto.ViewReservationDto;
import com.fishingbooker.ftn.service.interfaces.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/complaints")
public class ComplaintController {

    private final DataConverter converter;
    private final ComplaintService complaintService;

    @GetMapping("/available-complaints/{clientId}")
    public List<ViewReservationDto> getAvailableClientReviews(@PathVariable Long clientId) {
        List<Reservation> reservations = complaintService.getAvailableClientComplaints(clientId);
        return converter.convert(reservations, ViewReservationDto.class);
    }

    @PostMapping("/new-complaint")
    public void writeComplaint(@RequestBody ComplaintDto dto) {
        complaintService.create(dto);
    }

    @GetMapping("/get-waiting-complaints")
    public List<ComplaintDto> getWaitingCompolaints(){
        List<Complaint> complaints=complaintService.getWaitingComplaints();
        List<ComplaintDto> complaintDtos=converter.convert(complaints,ComplaintDto.class);
        return  complaintDtos;
    }

    @PostMapping("/create-response")
    public void createResponse(@RequestBody ComplaintResponseDto dto){
        complaintService.createResponse(dto);
    }

}
