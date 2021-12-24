package com.fishingbooker.ftn.bom.users;


import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.RequestApproval;
import jdk.nashorn.internal.ir.RuntimeNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class DeleteAccountRequest extends DatabaseEntity {

    @Column(nullable = false,name = "userId")
    private Long userId;

    @Column(nullable = false,name="description")
    private String description;

    @Column(nullable = false,name = "requestStatus")
    private RequestApproval requestStatus;
}
