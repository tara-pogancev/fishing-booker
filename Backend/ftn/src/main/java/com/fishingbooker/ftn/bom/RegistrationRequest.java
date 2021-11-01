package com.fishingbooker.ftn.bom;

import com.fishingbooker.ftn.bom.users.*;
import lombok.Data;
import org.dom4j.tree.AbstractEntity;

import javax.persistence.*;

@Entity
@Data
@Table(name="RegistrationRequest")
public class RegistrationRequest extends DatabaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private RegisteredUser user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fishingInstructor_id", referencedColumnName = "id")
    private FishingInstructor fishingInstructor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cottageOwner_id", referencedColumnName = "id")
    private CottageOwner cottageOwner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "boatOwner_id", referencedColumnName = "id")
    private BoatOwner boatOwner;

    @Column(name = "requestStatus",nullable = false)
    private RequestApproval approved=RequestApproval.WAITING;

    @Column(name="causesOfRejection",nullable = true)
    private String causesOfRejection;


}
