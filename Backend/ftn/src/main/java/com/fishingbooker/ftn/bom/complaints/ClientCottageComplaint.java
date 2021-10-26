package com.fishingbooker.ftn.bom.complaints;


import com.fishingbooker.ftn.bom.cottages.Cottage;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "ClientCottageComplaint")
public class ClientCottageComplaint extends Complaint {

    @ManyToOne
    @JoinColumn(name = "cottage", nullable = false)
    private Cottage cottage;

}
