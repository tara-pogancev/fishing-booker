package com.fishingbooker.ftn.bom.reservation_report;

import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.cottages.CottageReservation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "CottageReservationReport")
public class CottageReservationReport extends DatabaseEntity {
    @OneToOne
    @JoinColumn(name = "cottage_reservation", referencedColumnName = "id")
    private CottageReservation cottageReservation;

    @Column(name = "comment")
    private String comment;

    @Column(name = "reportStatus")
    private ReservationReportStatus reservationReportStatus;

    @Column(name = "processed_by_admin")
    private boolean processedByAdmin;
}
