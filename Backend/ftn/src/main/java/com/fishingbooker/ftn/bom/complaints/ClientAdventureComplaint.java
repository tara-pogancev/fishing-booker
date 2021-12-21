package com.fishingbooker.ftn.bom.complaints;

import com.fishingbooker.ftn.bom.adventures.Adventure;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "ClientAdventureComplaint")
public class ClientAdventureComplaint extends Complaint {

    @ManyToOne
    @JoinColumn(name = "adventure", nullable = false)
    private Adventure adventure;

}
