package com.fishingbooker.ftn.bom.reservation_report;

import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "adventureReservationReport")
@Entity
@Setter
@Getter
public class AdventureReservationReport extends DatabaseEntity {

    @OneToOne
    @JoinColumn(name = "adventure_reservation", referencedColumnName = "id")
    private AdventureReservation adventureReservation;

    @Column(name = "comment")
    private String comment;

    @Column(name = "reportStatus")
    private ReservationReportStatus reservationReportStatus;

    @Column(name = "processed_by_admin")
    private boolean processedByAdmin;

}
