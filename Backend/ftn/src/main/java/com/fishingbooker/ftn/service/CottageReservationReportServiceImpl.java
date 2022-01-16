package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.cottages.CottageReservation;
import com.fishingbooker.ftn.bom.reservation_report.CottageReservationReport;
import com.fishingbooker.ftn.bom.reservation_report.CottageReservationReport;
import com.fishingbooker.ftn.bom.reservation_report.ReservationReportStatus;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.dto.CreateAdventureReservationReportDto;
import com.fishingbooker.ftn.dto.ReservationReportDto;
import com.fishingbooker.ftn.repository.CottageReservationReportRepository;
import com.fishingbooker.ftn.repository.CottageReservationRepository;
import com.fishingbooker.ftn.repository.RegisteredClientRepository;
import com.fishingbooker.ftn.service.interfaces.CottageReservationReportService;
import com.fishingbooker.ftn.service.interfaces.MailingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CottageReservationReportServiceImpl implements CottageReservationReportService {

    private final CottageReservationReportRepository cottageReservationReportRepository;
    private final CottageReservationRepository cottageReservationRepository;
    private final RegisteredClientRepository registeredClientRepository;
    private final MailingService mailingService;

    @Override
    public boolean existsReportForCottageReservation(Long cottageReservationId) {
        CottageReservationReport report = cottageReservationReportRepository.getReport(cottageReservationId);
        return report != null;
    }

    @Override
    public Long createCottageReservationReport(CreateAdventureReservationReportDto cottageReportDto) {
        CottageReservationReport cottageReservationReport = new CottageReservationReport();
        CottageReservation cottageReservation = cottageReservationRepository.getById(cottageReportDto.getReservationId());
        cottageReservationReport.setComment(cottageReportDto.getComment());
        cottageReservationReport.setProcessedByAdmin(false);
        cottageReservationReport.setCottageReservation(cottageReservation);
        cottageReservationReport.setReservationReportStatus(ReservationReportStatus.POSITIVE);
        if (cottageReportDto.isBadComment()) {
            cottageReservationReport.setReservationReportStatus(ReservationReportStatus.NEGATIVE);
        } else if (cottageReportDto.isNoClient()) {
            cottageReservationReport.setReservationReportStatus(ReservationReportStatus.NO_CLIENT);
            cottageReservationReport.setProcessedByAdmin(true);
            RegisteredClient registeredClient = registeredClientRepository.getById(cottageReservation.getReservationClient().getId());
            registeredClient.setPenalties(registeredClient.getPenalties() + 1);
            registeredClientRepository.save(registeredClient);
        }
        return cottageReservationReportRepository.save(cottageReservationReport).getId();
    }

    @Override
    public Long givePenaltyForCottageReservation(ReservationReportDto reportDto) {
        RegisteredClient client;
        CottageReservationReport report = cottageReservationReportRepository.getById(reportDto.getReportId());
        report.setProcessedByAdmin(true);
        cottageReservationReportRepository.save(report);
        client = registeredClientRepository.getById(report.getCottageReservation().getReservationClient().getId());
        client.setPenalties(client.getPenalties() + 1);
        registeredClientRepository.save(client);
        mailingService.sendMailToUsersAboutGivingPenalty(client, report.getCottageReservation().getCottage().getCottageOwner());
        return report.getId();
    }
}
