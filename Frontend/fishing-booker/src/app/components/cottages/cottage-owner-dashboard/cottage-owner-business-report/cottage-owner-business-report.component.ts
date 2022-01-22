import { Component, Input, OnInit } from '@angular/core';
import { ReservationModel } from 'src/app/model/reservation-model';
import { CottageOwnerService } from 'src/app/service/cottage-owner.service';

@Component({
  selector: 'app-cottage-owner-business-report',
  templateUrl: './cottage-owner-business-report.component.html',
  styleUrls: ['./cottage-owner-business-report.component.css']
})
export class CottageOwnerBusinessReportComponent implements OnInit {
  @Input() cottageOwner: any;
  startDate = new Date();
  endDate = new Date();
  reservations: ReservationModel[] = [];
  allReservations: ReservationModel[] = [];
  price = 0;

  constructor(private cottageOwnerService: CottageOwnerService) { }

  ngOnInit(): void {
    this.cottageOwnerService.getPastReservations(this.cottageOwner.id).subscribe(data => {
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
