package com.fishingbooker.ftn.bom.complaints;

import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.EntityType;
import com.fishingbooker.ftn.bom.RequestApproval;
import com.fishingbooker.ftn.bom.reservations.Reservation;
import com.fishingbooker.ftn.bom.users.ApplicationUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Complaint")
public class Complaint extends DatabaseEntity {

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "approved", nullable = false)
    private RequestApproval approval = RequestApproval.WAITING;

    @Column(name = "type")
    private ReservationComplaintType type = ReservationComplaintType.NEGATIVE;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private ApplicationUser user;

    @ManyToOne
    @JoinColumn(name = "reservationId", nullable = false)
    private Reservation reservation;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "reservationType")
    private EntityType reservationType;

    @Column(name = "entityId", nullable = false)
    private Long entityId;

}
