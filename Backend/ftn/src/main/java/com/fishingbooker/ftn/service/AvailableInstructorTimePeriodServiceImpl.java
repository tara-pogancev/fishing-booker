package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.bom.adventures.AdventureQuickReservation;
import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.bom.adventures.AvailableInstructorTimePeriod;
import com.fishingbooker.ftn.bom.users.FishingInstructor;
import com.fishingbooker.ftn.conversion.UnixTimeToLocalDateTimeConverter;
import com.fishingbooker.ftn.dto.AvailableInstructorTimePeriodDto;
import com.fishingbooker.ftn.dto.ChangeTimeSlotDto;
import com.fishingbooker.ftn.repository.AdventureQuickReservationRepository;
import com.fishingbooker.ftn.repository.AdventureReservationRepository;
import com.fishingbooker.ftn.repository.AvailableInstructorTimeRepository;
import com.fishingbooker.ftn.repository.FishingInstructorRepository;
import com.fishingbooker.ftn.service.interfaces.AvailableInstructorTimePeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailableInstructorTimePeriodServiceImpl implements AvailableInstructorTimePeriodService {

    private final FishingInstructorRepository instructorRepository;
    private final AvailableInstructorTimeRepository availableInstructorTimeRepository;
    private final AdventureReservationRepository adventureReservationRepository;
    private final AdventureQuickReservationRepository adventureQuickReservationRepository;
    @Override
    public List<AvailableInstructorTimePeriod> findAll(Long instructorId) {
        FishingInstructor instructor=instructorRepository.getById(instructorId);
        List<AvailableInstructorTimePeriod> instructorTimePeriods=new ArrayList<>(instructor.getAvailableTimePeriods());
        return instructorTimePeriods;
    }

    @Override
    public Long create(AvailableInstructorTimePeriodDto availableTime) {
        if (!validate(availableTime.getInstructorId(),UnixTimeToLocalDateTimeConverter.TimeStampToDate(availableTime.getStartDate()),UnixTimeToLocalDateTimeConverter.TimeStampToDate(availableTime.getEndDate()))){
            return -1l;
        }
        if (availableTime.getId()==-1){
            AvailableInstructorTimePeriod availableInstructorTimePeriod=new AvailableInstructorTimePeriod();
            FishingInstructor instructor=instructorRepository.getById(availableTime.getInstructorId());
            availableInstructorTimePeriod.setEndDate(UnixTimeToLocalDateTimeConverter.TimeStampToDate(availableTime.getEndDate()));
            availableInstructorTimePeriod.setStartDate(UnixTimeToLocalDateTimeConverter.TimeStampToDate(availableTime.getStartDate()));
            availableInstructorTimePeriod.setInstructor(instructor);
            return availableInstructorTimeRepository.save(availableInstructorTimePeriod).getId();
        }else{
            AvailableInstructorTimePeriod availableInstructorTimePeriod=availableInstructorTimeRepository.get(availableTime.getId());
            availableInstructorTimePeriod.setEndDate(UnixTimeToLocalDateTimeConverter.TimeStampToDate(availableTime.getEndDate()));
            availableInstructorTimePeriod.setStartDate(UnixTimeToLocalDateTimeConverter.TimeStampToDate(availableTime.getStartDate()));
            return availableInstructorTimeRepository.save(availableInstructorTimePeriod).getId();
        }
    }

    private boolean validate(Long instructorId, LocalDateTime startDate,LocalDateTime endDate){
       return  availableInstructorTimeRepository.checkOverlaping(instructorId,startDate,endDate).size()==0? true:false;
    }

    @Override
    public boolean delete(Long id) {
        AvailableInstructorTimePeriod availableInstructorTimePeriod=availableInstructorTimeRepository.get(id);
        FishingInstructor instructor=availableInstructorTimePeriod.getInstructor();
        List<Adventure> adventures=new ArrayList<>(instructor.getAdventures());
        for(Adventure adventure:adventures){
            List<AdventureReservation> reservationsInSelectedDate=adventureReservationRepository.getInSelectedDate(availableInstructorTimePeriod.getStartDate(),availableInstructorTimePeriod.getEndDate(),adventure.getId());
            if (reservationsInSelectedDate.size()>0){
                return false;
            }
            List<AdventureQuickReservation> adventureQuickReservations=adventureQuickReservationRepository.getInSelectedDate(availableInstructorTimePeriod.getStartDate(),availableInstructorTimePeriod.getEndDate(),adventure.getId());
            if (adventureQuickReservations.size()>0){
                return false;
            }
        }
        availableInstructorTimeRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean update(ChangeTimeSlotDto dto) {
        if (!validate(dto.getInstructorId(),UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getNewStartDate()),UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getNewEndDate()))){
            return false;
        }
        FishingInstructor instructor=instructorRepository.getById(dto.getInstructorId());
        List<Adventure> adventures=new ArrayList<>(instructor.getAdventures());
        for(Adventure adventure:adventures){
            List<AdventureReservation> reservationsInSelectedDate=adventureReservationRepository.getInSelectedDate(UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getStartDate()),UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getEndDate()),adventure.getId());
            List<AdventureReservation> reservationsInNewSelectedDate=adventureReservationRepository.getInSelectedDate(UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getNewStartDate()),UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getNewEndDate()),adventure.getId());
            if (reservationsInSelectedDate.size()!=reservationsInNewSelectedDate.size()){
                return false;
            }
            List<AdventureQuickReservation> quickReservationsInSelectedDate=adventureQuickReservationRepository.getInSelectedDate(UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getStartDate()),UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getEndDate()),adventure.getId());
            List<AdventureQuickReservation> quickReservationsInNewSelectedDate=adventureQuickReservationRepository.getInSelectedDate(UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getNewStartDate()),UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getNewEndDate()),adventure.getId());
            if (quickReservationsInSelectedDate.size()!=quickReservationsInNewSelectedDate.size()){
                return false;
            }
        }
        AvailableInstructorTimePeriod period=availableInstructorTimeRepository.get(dto.getId());
        period.setStartDate(UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getNewStartDate()));
        period.setEndDate(UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getNewEndDate()));
        availableInstructorTimeRepository.save(period);
        return true;
    }
}
