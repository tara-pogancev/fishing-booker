package com.fishingbooker.ftn.bom;

import com.fishingbooker.ftn.bom.users.RegisteredUser;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ReservationReport")
public class ReservationReport extends DatabaseEntity {

    @ManyToOne
    @JoinColumn(name = "client", nullable = false)
    private RegisteredUser client;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "type", nullable = false)
    private ReservationReportType type;

    @Column(name = "approved", nullable = false)
    private RequestApproval approved = RequestApproval.Waiting;

}
