package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.bom.reservation_report.AdventureReservationReport;
import com.fishingbooker.ftn.bom.reservation_report.ReservationReportStatus;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.dto.CreateAdventureReservationReportDto;
import com.fishingbooker.ftn.dto.ReservationReportDto;
import com.fishingbooker.ftn.repository.AdventureReservationReportRepository;
import com.fishingbooker.ftn.repository.AdventureReservationRepository;
import com.fishingbooker.ftn.repository.RegisteredClientRepository;
import com.fishingbooker.ftn.service.interfaces.AdventureReservationReportService;
import com.fishingbooker.ftn.service.interfaces.MailingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdventureReservationReportServiceImpl implements AdventureReservationReportService {

    private final AdventureReservationReportRepository adventureReservationReportRepository;
    private final AdventureReservationRepository adventureReservationRepository;
    private final RegisteredClientRepository registeredClientRepository;
    private final MailingService mailingService;

    @Override
    public List<AdventureReservationReport> getUnprocessedReports() {
        List<AdventureReservationReport> reservationReports=adventureReservationReportRepository.getUnprocessedReports();
        return reservationReports;
    }

    @Override
    public Long createAdventureReservationReport(CreateAdventureReservationReportDto adventureReportDto) {
        AdventureReservationReport adventureReservationReport=new AdventureReservationReport();
        AdventureReservation adventureReservation=adventureReservationRepository.getById(adventureReportDto.getReservationId());
        adventureReservationReport.setComment(adventureReportDto.getComment());
        adventureReservationReport.setProcessedByAdmin(false);
        adventureReservationReport.setAdventureReservation(adventureReservation);
        adventureReservationReport.setReservationReportStatus(ReservationReportStatus.POSITIVE);
        if (adventureReportDto.isBadComment()==true){
            adventureReservationReport.setReservationReportStatus(ReservationReportStatus.NEGATIVE);
        }else if(adventureReportDto.isNoClient()==true){
            adventureReservationReport.setReservationReportStatus(ReservationReportStatus.NO_CLIENT);
            adventureReservationReport.setProcessedByAdmin(true);
            RegisteredClient registeredClient=registeredClientRepository.getById(adventureReservation.getReservationClient().getId());
            registeredClient.setPenalties(registeredClient.getPenalties()+1);
            registeredClientRepository.save(registeredClient);
        }
        return adventureReservationReportRepository.save(adventureReservationReport).getId();
    }

    @Override
    public Long givePenaltyToClient(ReservationReportDto reportDto) {
        if (reportDto.getReportType().equalsIgnoreCase("Adventure")){
            return givePenaltyForAdventureReservation(reportDto);
        }
        //todo for other type of reservation

        return null;

    }

    @Override
    public Long forgiveClient(ReservationReportDto reportDto) {
        if (reportDto.getReportType().equalsIgnoreCase("Adventure")){
            return forgiveForAdventureReservation(reportDto);
        }

        return null;
    }

    private Long forgiveForAdventureReservation(ReservationReportDto reportDto) {
        RegisteredClient client;
        AdventureReservationReport report=adventureReservationReportRepository.getById(reportDto.getReportId());
        report.setProcessedByAdmin(true);
        adventureReservationReportRepository.save(report);
        client=registeredClientRepository.getById(report.getAdventureReservation().getReservationClient().getId());
        mailingService.sendMailToUsersAboutNotGivingPenalty(client,report.getAdventureReservation().getAdventure().getInstructor());
        return report.getId();
    }

    private Long givePenaltyForAdventureReservation(ReservationReportDto reportDto) {
        RegisteredClient client;
        AdventureReservationReport report=adventureReservationReportRepository.getById(reportDto.getReportId());
        report.setProcessedByAdmin(true);
        adventureReservationReportRepository.save(report);
        client=registeredClientRepository.getById(report.getAdventureReservation().getReservationClient().getId());
        client.setPenalties(client.getPenalties()+1);
        registeredClientRepository.save(client);
        mailingService.sendMailToUsersAboutGivingPenalty(client,report.getAdventureReservation().getAdventure().getInstructor());
        return report.getId();
    }

    public boolean existsReportForAdventureReservation(Long adventureReservationId){
        AdventureReservationReport report=adventureReservationReportRepository.getReport(adventureReservationId);
        return report!=null;
    }
}
