package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.bom.cottages.CottageUtility;
import com.fishingbooker.ftn.bom.cottages.Room;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.CottageDto;
import com.fishingbooker.ftn.dto.RoomDto;
import com.fishingbooker.ftn.dto.UtilityDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CottageToDto implements Converter<Cottage, CottageDto> {

    private final ModelMapper modelMapper;

    @Override
    public CottageDto convert(Cottage source) {
        CottageDto dto = modelMapper.map(source, CottageDto.class);
        dto.setOwnerName(source.getCottageOwner().getName() + " " + source.getCottageOwner().getLastName());
        dto.setRules(DataConverter.getRules(source.getRules()));
        dto.setUtilities(getUtilityDtoList(source.getUtilities()));
        dto.setImageUrls(source.getImages().stream().map(image -> image.getUrl()).collect(Collectors.toSet()));
        return dto;
    }

    public Set<RoomDto> getRoomDtoSet(Set<Room> rooms) {
        Set<RoomDto> dto = new HashSet<>();
        for (Room room : rooms) {
            RoomDto roomDto = new RoomDto();
            roomDto.setId(room.getId());
            roomDto.setNumberOfBeds(room.getNumberOfBeds());
            dto.add(roomDto);
        }
        return dto;
    }

    private Set<UtilityDto> getUtilityDtoList(Set<CottageUtility> source) {
        Set<UtilityDto> retVal = new HashSet<>();
        for (CottageUtility utility : source) {
            UtilityDto dto = new UtilityDto();
            dto.setName(utility.getUtility().getName());
            dto.setPrice(utility.getPrice());
            dto.setId(utility.getId());
            retVal.add(dto);
        }
        return retVal;
    }

}
