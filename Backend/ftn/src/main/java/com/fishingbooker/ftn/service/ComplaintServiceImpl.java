package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.EntityType;
import com.fishingbooker.ftn.bom.RequestApproval;
import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.bom.complaints.Complaint;
import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.bom.cottages.CottageReservation;
import com.fishingbooker.ftn.bom.reservations.Reservation;
import com.fishingbooker.ftn.bom.users.ApplicationUser;
import com.fishingbooker.ftn.dto.ComplaintDto;
import com.fishingbooker.ftn.dto.ComplaintResponseDto;
import com.fishingbooker.ftn.repository.AdventureRepository;
import com.fishingbooker.ftn.repository.BoatRepository;
import com.fishingbooker.ftn.repository.ComplaintRepository;
import com.fishingbooker.ftn.repository.CottageRepository;
import com.fishingbooker.ftn.service.interfaces.ApplicationUserService;
import com.fishingbooker.ftn.service.interfaces.ComplaintService;
import com.fishingbooker.ftn.service.interfaces.MailingService;
import com.fishingbooker.ftn.service.interfaces.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ApplicationUserService userService;
    private final ReservationService reservationService;
    private final ComplaintRepository complaintRepository;
    private final BoatRepository boatRepository;
    private final AdventureRepository adventureRepository;
    private final CottageRepository cottageRepository;
    private final MailingService mailingService;

    @Override
    public void create(ComplaintDto dto) {
        if (reservationService.find(dto.id) != null) {

            CottageReservation cottageReservation = reservationService.findCottageReservation(dto.id);
            BoatReservation boatReservation = reservationService.findBoatReservation(dto.id);
            AdventureReservation adventureReservation = reservationService.findAdventureReservation(dto.id);

            if (cottageReservation != null) {
                saveCottageComplaint(dto, cottageReservation);
            } else if (boatReservation != null) {
                saveBoatComplaint(dto, boatReservation);
            } else if (adventureReservation != null) {
                saveAdventureComplaint(dto, adventureReservation);
            }
        }
    }

    private void saveAdventureComplaint(ComplaintDto dto, AdventureReservation adventureReservation) {
        Complaint complaint = new Complaint();
        complaint.setDescription(dto.description);
        complaint.setReservationType(EntityType.ADVENTURE);
        complaint.setEntityId(adventureReservation.getAdventure().getId());
        complaint.setUser(userService.findById(dto.userId));
        complaint.setReservation(adventureReservation);
        complaint.setDate(LocalDateTime.now());
        complaintRepository.save(complaint);
    }

    private void saveBoatComplaint(ComplaintDto dto, BoatReservation boatReservation) {
        Complaint complaint = new Complaint();
        complaint.setDescription(dto.description);
        complaint.setReservationType(EntityType.BOAT);
        complaint.setEntityId(boatReservation.getBoat().getId());
        complaint.setUser(userService.findById(dto.userId));
        complaint.setReservation(boatReservation);
        complaint.setDate(LocalDateTime.now());
        complaintRepository.save(complaint);
    }

    private void saveCottageComplaint(ComplaintDto dto, CottageReservation cottageReservation) {
        Complaint complaint = new Complaint();
        complaint.setDescription(dto.description);
        complaint.setReservationType(EntityType.COTTAGE);
        complaint.setEntityId(cottageReservation.getCottage().getId());
        complaint.setUser(userService.findById(dto.userId));
        complaint.setReservation(cottageReservation);
        complaint.setDate(LocalDateTime.now());
        complaintRepository.save(complaint);
    }

    @Override
    public List<Reservation> getAvailableClientComplaints(Long clientId) {
        List<Reservation> reservations = new ArrayList<>();

        for (Reservation reservation : reservationService.findAllByClient(clientId)) {
            if (reservation.getReservationEnd().isBefore(LocalDateTime.now())) {
                boolean hasComplaint = false;
                for (Complaint complaint : getComplaintsByUser(clientId))
                    if (complaint.getReservation().getId() == reservation.getId()) {
                        hasComplaint = true;
                        break;
                    }

                if (!hasComplaint)
                    reservations.add(reservation);
            }
        }

        return reservations;
    }

    @Override
    public List<Complaint> getComplaintsByUser(Long userId) {
        List<Complaint> complaints = new ArrayList<>();
        for (Complaint complaint : complaintRepository.findAll()) {
            if (complaint.getUser().getId() == userId)
                complaints.add(complaint);
        }
        return complaints;
    }

    @Override
    public List<Complaint> getWaitingComplaints() {
        return complaintRepository.getWaitingComplaints();
    }

    @Override
    public boolean createResponse(ComplaintResponseDto dto) {
        try {
            Complaint complaint = complaintRepository.getComplaint(dto.getComplaintId());
            if (complaint.getApproval() == RequestApproval.PROCESSED) {
                return false;
            }
            ApplicationUser client = complaint.getUser();
            complaint.setApproval(RequestApproval.PROCESSED);
            complaintRepository.save(complaint);
            sendNotificationMail(dto, complaint, client);
        } catch (PessimisticLockingFailureException e) {
            System.out.println("Locking exception");
            return false;
        }
        return true;
    }

    private void sendNotificationMail(ComplaintResponseDto dto, Complaint complaint, ApplicationUser client) {
        if (complaint.getReservationType() == EntityType.BOAT) {
            Boat boat = boatRepository.get(complaint.getEntityId());
            ApplicationUser owner = boat.getBoatOwner();
            mailingService.sendComplaintResponse(client, owner, dto.getResponse(), complaint.getDescription());
        } else if (complaint.getReservationType() == EntityType.ADVENTURE) {
            Adventure adventure = adventureRepository.getById(complaint.getEntityId());
            ApplicationUser owner = adventure.getInstructor();
            mailingService.sendComplaintResponse(client, owner, dto.getResponse(), complaint.getDescription());
        } else {
            Cottage cottage = cottageRepository.getById(complaint.getEntityId());
            ApplicationUser owner = cottage.getCottageOwner();
            mailingService.sendComplaintResponse(client, owner, dto.getResponse(), complaint.getDescription());
        }
    }
}
