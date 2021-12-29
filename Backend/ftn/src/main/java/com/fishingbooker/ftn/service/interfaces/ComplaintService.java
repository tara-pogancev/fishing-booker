package com.fishingbooker.ftn.service.interfaces;


import com.fishingbooker.ftn.bom.Review;
import com.fishingbooker.ftn.bom.complaints.Complaint;
import com.fishingbooker.ftn.bom.reservations.Reservation;
import com.fishingbooker.ftn.dto.ComplaintDto;

import java.util.List;

public interface ComplaintService {

    void create(ComplaintDto dto);

    List<Reservation> getAvailableClientComplaints(Long clientId);

    List<Complaint> getComplaintsByUser(Long userId);
}
