import { Component, OnInit } from '@angular/core';
import { AdventureReservationReport } from '../../../../model/adventure-reservation-report';
import { ReservationReportService } from '../../../../service/reservation-report';

@Component({
  selector: 'app-reservation-report',
  templateUrl: './reservation-report.component.html',
  styleUrls: ['./reservation-report.component.css']
})
export class ReservationReportComponent implements OnInit {

  reservationReports:AdventureReservationReport[]=[];
  constructor(private reservationReportService:ReservationReportService) { }

  ngOnInit(): void {
    this.reservationReportService.getReservationReportsForAdmin().subscribe(data=>{
      this.reservationReports=data;
    })
  }

   givePenalty(report:AdventureReservationReport){
      this.reservationReportService.givePenalty(report).subscribe(data=>{
        if (data!=null){
          alert('Successfully')
          this.removeFromFrontend(report);
        }
      });
   }

   forgiveClient(report:AdventureReservationReport){
    this.reservationReportService.forgiveClient(report).subscribe(data=>{
        if (data!=null){
          alert('Successfully!');
          this.removeFromFrontend(report);
        }
        
    });
  }
  removeFromFrontend(report:AdventureReservationReport){
    const index=this.reservationReports.indexOf(report);
    this.reservationReports.splice(index,1);
  }

   

}
