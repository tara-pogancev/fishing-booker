package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.users.DeleteAccountRequest;
import com.fishingbooker.ftn.bom.users.ApplicationRole;
import com.fishingbooker.ftn.bom.users.ApplicationUser;
import com.fishingbooker.ftn.dto.DeleteAccountRequestDto;
import com.fishingbooker.ftn.repository.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteAccountRequestToDto implements Converter<DeleteAccountRequest,DeleteAccountRequestDto> {

    private final ApplicationUserRepository applicationUserRepository;
    @Override
    public DeleteAccountRequestDto convert(DeleteAccountRequest source) {
        DeleteAccountRequestDto dto=new DeleteAccountRequestDto();
        ApplicationUser user=applicationUserRepository.get(source.getUserId());
        dto.setId(source.getId());
        dto.setName(user.getName());
        dto.setLastName(user.getLastName());
        dto.setDescription(source.getDescription());
        dto.setUserType(ApplicationRole.toString(user.getRole()));
        dto.setMail(user.getEmail());
        return dto;
    }
}
