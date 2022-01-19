package com.fishingbooker.ftn.bom.reservation_report;

import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.boats.BoatReservation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "BoatReservationReport")
public class BoatReservationReport extends DatabaseEntity {
    @OneToOne
    @JoinColumn(name = "boat_reservation", referencedColumnName = "id")
    private BoatReservation boatReservation;

    @Column(name = "comment")
    private String comment;

    @Column(name = "reportStatus")
    private ReservationReportStatus reservationReportStatus;

    @Column(name = "processed_by_admin")
    private boolean processedByAdmin;
}
