package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.Utility;
import com.fishingbooker.ftn.bom.boats.BoatUtility;
import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.bom.cottages.CottageUtility;
import com.fishingbooker.ftn.bom.cottages.Room;
import com.fishingbooker.ftn.dto.BoatDto;
import com.fishingbooker.ftn.dto.CottageDto;
import com.fishingbooker.ftn.dto.RoomDto;
import com.fishingbooker.ftn.dto.UtilityDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CottageToDto implements Converter<Cottage, CottageDto> {

    private final ModelMapper modelMapper;

    @Override
    public CottageDto convert(Cottage source) {
        CottageDto dto = modelMapper.map(source, CottageDto.class);
        dto.setOwnerName(source.getCottageOwner().getName() + " " + source.getCottageOwner().getLastName());
        dto.setRooms(getRoomDtoSet(source.getRooms()));
        dto.setUtilities(getUtilityDtoList(source.getUtilities()));
        return dto;
    }

    public Set<RoomDto> getRoomDtoSet(Set<Room> rooms) {
        Set<RoomDto> dto = new HashSet<>();
        for (Room room : rooms ) {
            RoomDto roomDto = new RoomDto();
            roomDto.setId(room.getId());
            roomDto.setNumberOfBeds(room.getNumberOfBeds());
            dto.add(roomDto);
        }
        return dto;
    }

    private Set<UtilityDto> getUtilityDtoList(Set<CottageUtility> source) {
        Set<UtilityDto> retVal = new HashSet<>();
        for (Utility utility: source) {
            retVal.add(modelMapper.map(utility, UtilityDto.class));
        }
        return retVal;
    }

}
