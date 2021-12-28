package com.fishingbooker.ftn.bom;


import com.fishingbooker.ftn.bom.reservations.Reservation;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Review extends DatabaseEntity {

    @Column(name = "review")
    private String review;

    @Column(name = "reservationType")
    private String reservationType;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "entityId", nullable = false)
    private Long entityId;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "isApproved", nullable = false)
    private RequestApproval approval = RequestApproval.WAITING;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private RegisteredClient client;

}
