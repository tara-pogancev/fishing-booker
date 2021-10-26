package com.fishingbooker.ftn.bom.complaints;

import com.fishingbooker.ftn.bom.adventures.Adventure;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "ClientAdventureComplaint")
public class ClientAdventureComplaint extends Complaint {

    @ManyToOne
    @JoinColumn(name = "adventure", nullable = false)
    private Adventure adventure;

}
