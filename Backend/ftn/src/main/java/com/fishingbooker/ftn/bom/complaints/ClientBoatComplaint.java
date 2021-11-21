package com.fishingbooker.ftn.bom.complaints;

import com.fishingbooker.ftn.bom.boats.Boat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "ClientBoatComplaint")
public class ClientBoatComplaint extends Complaint {

    @ManyToOne
    @JoinColumn(name = "boat", nullable = false)
    private Boat boat;

}