import { Component, OnInit } from '@angular/core';
import { InstructorPastReservation } from '../../../../model/instructor-past-reservations';
import { FishingInstructorService } from '../../../../service/fishing-instructor.service';

@Component({
  selector: 'app-reservations-history',
  templateUrl: './reservations-history.component.html',
  styleUrls: ['./reservations-history.component.css']
})
export class ReservationsHistoryComponent implements OnInit {

  reservations:InstructorPastReservation[]=[];
  constructor(private fishingInstructorService:FishingInstructorService) { }

  openModalUser(reservation:InstructorPastReservation){
    document.getElementById('modalUser')?.classList.toggle('is-active');
  }

  closeModalUser(){
    document.getElementById('modalUser')?.classList.toggle('is-active');
  }

  openModalReport(reservation:InstructorPastReservation){
    window.location.href='adventure-reservation-report/'+reservation.reservationId;
  }

  closeModalReport(){
    document.getElementById('modalReport')?.classList.toggle('is-active');
  }

  ngOnInit(): void {
    this.fishingInstructorService.getInstructorPastReservations().subscribe(data=>{
      this.reservations=data;
    })
  }

}
