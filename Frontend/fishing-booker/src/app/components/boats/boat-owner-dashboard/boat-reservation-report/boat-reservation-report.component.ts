import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CreateAdventureReservationReport } from 'src/app/model/create-advenutre-reservation-report';
import { ReservationReportService } from 'src/app/service/reservation-report';

@Component({
  selector: 'app-boat-reservation-report',
  templateUrl: './boat-reservation-report.component.html',
  styleUrls: ['./boat-reservation-report.component.css']
})
export class BoatReservationReportComponent implements OnInit {
  id:string='0';
  reservationReport:CreateAdventureReservationReport=new CreateAdventureReservationReport();
  value:string='';

  constructor(private route: ActivatedRoute,private reservationReportService:ReservationReportService) { }

  ngOnInit(): void {
    this.id=this.route.snapshot.paramMap.get('id')!;
    this.reservationReport.reservationId=Number(this.id);
  }

  onItemChange(event:any){
    if (event!=null){
      if (event==='noClient'){
        this.reservationReport.noClient=true;
        this.reservationReport.badComment=false;
      }else if(event==='negative'){
        this.reservationReport.badComment=true;
        this.reservationReport.noClient=false;
      }else{
        this.reservationReport.badComment=false;
        this.reservationReport.noClient=false;
      }

    }
  }

  saveReport(){
    this.reservationReportService.createBoatReservationReport(this.reservationReport).subscribe(data=>{
      if (data==null){
        alert('Error during creating report');
      }else{
        alert('Report successfully created!');
        window.location.href='/boat-owner-db';
      }
    })
  }

}
