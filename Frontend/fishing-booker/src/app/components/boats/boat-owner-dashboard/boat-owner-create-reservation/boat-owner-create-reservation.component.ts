import { Component, Input, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';
import { NewReservation } from 'src/app/model/new-reservation';
import { Utility } from 'src/app/model/utility-model';
import { BoatService } from 'src/app/service/boat.service';
import { ClientService } from 'src/app/service/client.service';

@Component({
  selector: 'app-boat-owner-create-reservation',
  templateUrl: './boat-owner-create-reservation.component.html',
  styleUrls: ['./boat-owner-create-reservation.component.css']
})
export class BoatOwnerCreateReservationComponent implements OnInit {
  @Input() boatOwner: any;
  clients: Client[] = [];
  boats: any = [];
  validForm: boolean = true;
  reservation: NewReservation = new NewReservation();
  startDate: Date = new Date();
  endDate: Date = new Date();
  utilities: Utility[] = [];

  constructor(private clientService: ClientService, private boatService: BoatService) { }

  ngOnInit(): void {
    this.boatService.findByBoatOwnerId(this.boatOwner.id).subscribe((data: any) => this.boats = data);
    this.clientService.getClientsWithBoatReservationAtTheMoment(this.boatOwner.id).subscribe(data => {
      this.clients = data;
    })
  }

  validateForm(): void {
    this.validForm = true;
    this.reservation.startDate = new Date(this.startDate).getTime() / 1000;
    this.reservation.endDate = new Date(this.endDate).getTime() / 1000;
    if (this.reservation.clientId == -1 || this.reservation.entityId == -1 || this.reservation.endDate == -1 ||
      this.reservation.startDate == -1 || this.reservation.startDate > this.reservation.endDate
      || this.reservation.startDate < new Date().getTime() / 1000 ||
      this.reservation.endDate < new Date().getTime() / 1000) {
      this.validForm = false;
      return;
    }
    this.boatService.createBoatReservation(this.reservation).subscribe(data => {
      if (data == -1) {
        alert('Reservation in selected date already exists or boat is not available');
      }
      else {
        alert('Reservation is created successfully');
      }
    });
  }

  removeService(service: Utility) {
    const index = this.reservation.utilities.indexOf(service);
    this.reservation.utilities.splice(index, 1);
  }

  boatChosen(event: any) {
    this.boatService.getBoatUtilities(this.reservation.entityId).subscribe(data => {
      this.reservation.utilities.length = 0;
      this.reservation.utilities = data;
    })
  }

}
