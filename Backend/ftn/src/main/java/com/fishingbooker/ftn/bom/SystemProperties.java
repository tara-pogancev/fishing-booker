package com.fishingbooker.ftn.bom;

import com.fishingbooker.ftn.bom.users.Administrator;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "SystemProperties")
public class SystemProperties extends DatabaseEntity {

    //todo: pitati asistenta kako ovo ide i gde se cuvaju ovi podaci, da li kao properties ili ne?
    // Da li se pravi nova rola za superadmina zbog scuiritija

    @Column(name = "incomePercentage", nullable = false)
    private Double incomePercentage;

    @OneToOne
    @JoinColumn(name = "super_admin_id")
    private Administrator superAdmin;

}
