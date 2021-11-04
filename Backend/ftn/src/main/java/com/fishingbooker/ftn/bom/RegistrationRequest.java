package com.fishingbooker.ftn.bom;

import com.fishingbooker.ftn.bom.users.BoatOwner;
import com.fishingbooker.ftn.bom.users.CottageOwner;
import com.fishingbooker.ftn.bom.users.FishingInstructor;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "RegistrationRequest")
public class RegistrationRequest extends DatabaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private RegisteredClient user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fishingInstructor_id", referencedColumnName = "userId")
    private FishingInstructor fishingInstructor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cottageOwner_id", referencedColumnName = "userId")
    private CottageOwner cottageOwner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "boatOwner_id", referencedColumnName = "userId")
    private BoatOwner boatOwner;

    @Column(name = "requestStatus", nullable = false)
    private RequestApproval approved = RequestApproval.WAITING;

    @Column(name = "causesOfRejection", nullable = true)
    private String causesOfRejection;

}
