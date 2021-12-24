package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.users.DeleteAccountRequest;

import java.util.List;

public interface DeleteAccountService {

    List<DeleteAccountRequest> get();

    boolean approve(Long requestId,String description);

    boolean reject(Long requestId);

    Long create(DeleteAccountRequest deleteAccountRequest);
}
