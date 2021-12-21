package com.fishingbooker.ftn.bom.complaints;


import com.fishingbooker.ftn.bom.cottages.Cottage;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "ClientCottageComplaint")
public class ClientCottageComplaint extends Complaint {

    @ManyToOne
    @JoinColumn(name = "cottage", nullable = false)
    private Cottage cottage;

}
