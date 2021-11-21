package com.fishingbooker.ftn.bom.complaints;

import com.fishingbooker.ftn.bom.users.RegisteredClient;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "ReservationComplaint")
public class ReservationComplaint extends Complaint {

    @ManyToOne
    @JoinColumn(name = "client", nullable = false)
    private RegisteredClient client;

    @Column(name = "type", nullable = false)
    private ReservationComplaintType type;

}
