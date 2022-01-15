package com.fishingbooker.ftn.service.interfaces;


import com.fishingbooker.ftn.bom.complaints.Complaint;
import com.fishingbooker.ftn.bom.reservations.Reservation;
import com.fishingbooker.ftn.dto.ComplaintDto;
import com.fishingbooker.ftn.dto.ComplaintResponseDto;

import java.util.List;

public interface ComplaintService {

    void create(ComplaintDto dto);

    List<Reservation> getAvailableClientComplaints(Long clientId);

    List<Complaint> getComplaintsByUser(Long userId);

    List<Complaint> getWaitingComplaints();

    boolean createResponse(ComplaintResponseDto dto);
}
