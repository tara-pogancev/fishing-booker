package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.bom.reservation_report.AdventureReservationReport;
import com.fishingbooker.ftn.bom.reservation_report.BoatReservationReport;
import com.fishingbooker.ftn.bom.reservation_report.CottageReservationReport;
import com.fishingbooker.ftn.bom.reservation_report.ReservationReportStatus;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.dto.CreateAdventureReservationReportDto;
import com.fishingbooker.ftn.dto.ReservationReportDto;
import com.fishingbooker.ftn.repository.BoatReservationReportRepository;
import com.fishingbooker.ftn.repository.BoatReservationRepository;
import com.fishingbooker.ftn.repository.RegisteredClientRepository;
import com.fishingbooker.ftn.service.interfaces.BoatReservationReportService;
import com.fishingbooker.ftn.service.interfaces.MailingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoatReservationReportServiceImpl implements BoatReservationReportService {

    private final BoatReservationReportRepository boatReservationReportRepository;
    private final BoatReservationRepository boatReservationRepository;
    private final RegisteredClientRepository registeredClientRepository;
    private final MailingService mailingService;

    @Override
    public boolean existsReportForBoatReservation(Long boatReservationId) {
        BoatReservationReport report = boatReservationReportRepository.getReport(boatReservationId);
        return report != null;
    }

    @Override
    public Long createBoatReservationReport(CreateAdventureReservationReportDto boatReportDto) {
        BoatReservationReport boatReservationReport = new BoatReservationReport();
        BoatReservation boatReservation = boatReservationRepository.getById(boatReportDto.getReservationId());
        boatReservationReport.setComment(boatReportDto.getComment());
        boatReservationReport.setProcessedByAdmin(false);
        boatReservationReport.setBoatReservation(boatReservation);
        boatReservationReport.setReservationReportStatus(ReservationReportStatus.POSITIVE);
        if (boatReportDto.isBadComment()) {
            boatReservationReport.setReservationReportStatus(ReservationReportStatus.NEGATIVE);
        } else if (boatReportDto.isNoClient()) {
            boatReservationReport.setReservationReportStatus(ReservationReportStatus.NO_CLIENT);
            boatReservationReport.setProcessedByAdmin(true);
            RegisteredClient registeredClient = registeredClientRepository.getById(boatReservation.getReservationClient().getId());
            registeredClient.setPenalties(registeredClient.getPenalties() + 1);
            registeredClientRepository.save(registeredClient);
        }
        return boatReservationReportRepository.save(boatReservationReport).getId();
    }

    @Override
    public Long givePenaltyForBoatReservation(ReservationReportDto reportDto) {
        RegisteredClient client;
        BoatReservationReport report = boatReservationReportRepository.getById(reportDto.getReportId());
        report.setProcessedByAdmin(true);
        boatReservationReportRepository.save(report);
        client = registeredClientRepository.getById(report.getBoatReservation().getReservationClient().getId());
        client.setPenalties(client.getPenalties() + 1);
        registeredClientRepository.save(client);
        mailingService.sendMailToUsersAboutGivingPenalty(client, report.getBoatReservation().getBoat().getBoatOwner());
        return report.getId();
    }

    @Override
    public List<BoatReservationReport> getUnprocessedReports() {
        return boatReservationReportRepository.getUnprocessedReports();
    }

    @Override
    public Long forgiveClient(ReservationReportDto reportDto) {
        RegisteredClient client;
        BoatReservationReport report = boatReservationReportRepository.getById(reportDto.getReportId());
        report.setProcessedByAdmin(true);
        boatReservationReportRepository.save(report);
        client = registeredClientRepository.getById(report.getBoatReservation().getReservationClient().getId());
        mailingService.sendMailToUsersAboutNotGivingPenalty(client, report.getBoatReservation().getBoat().getBoatOwner());
        return report.getId();
    }
}
