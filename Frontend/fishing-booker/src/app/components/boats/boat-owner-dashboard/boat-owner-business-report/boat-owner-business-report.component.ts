import { Component, Input, OnInit } from '@angular/core';
import { ReservationModel } from 'src/app/model/reservation-model';
import { BoatOwnerService } from 'src/app/service/boat-owner.service';

@Component({
  selector: 'app-boat-owner-business-report',
  templateUrl: './boat-owner-business-report.component.html',
  styleUrls: ['./boat-owner-business-report.component.css']
})
export class BoatOwnerBusinessReportComponent implements OnInit {
  @Input() boatOwner: any;
  startDate = new Date();
  endDate = new Date();
  reservations: ReservationModel[] = [];
  allReservations: ReservationModel[] = [];
  price = 0;

  constructor(private boatOwnerService: BoatOwnerService) { }

  ngOnInit(): void {
    this.boatOwnerService.getPastReservations(this.boatOwner.id).subscribe(data => {
      this.allReservations = data;
      this.reservations = data;
      this.calculatePrice();
    })
  }

  calculatePrice() {
    this.price = 0;
    this.reservations.forEach((element: any) => {
      this.price += element.price;
    });
  }

  search() {
    console.log(this.startDate);
    console.log(this.endDate);
    this.reservations = [];
    for (let reservation of this.allReservations) {
      if (reservation.startDate >= this.startDate && reservation.endDate <= this.endDate) {
        this.reservations.push(reservation);
      }
    }
    this.calculatePrice();
  }

}
