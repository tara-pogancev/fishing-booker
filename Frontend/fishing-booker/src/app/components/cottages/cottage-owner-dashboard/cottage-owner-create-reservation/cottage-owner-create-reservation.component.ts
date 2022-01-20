import { Component, Input, OnInit } from '@angular/core';
import { AnonymousSubject } from 'rxjs/internal/Subject';
import { Client } from 'src/app/model/client-model';
import { Cottage } from 'src/app/model/cottage-model';
import { InstructorNewReservation } from 'src/app/model/instructor-new-reservation';
import { NewReservation } from 'src/app/model/new-reservation';
import { Utility } from 'src/app/model/utility-model';
import { ClientService } from 'src/app/service/client.service';
import { CottageService } from 'src/app/service/cottage.service';

@Component({
  selector: 'app-cottage-owner-create-reservation',
  templateUrl: './cottage-owner-create-reservation.component.html',
  styleUrls: ['./cottage-owner-create-reservation.component.css']
})
export class CottageOwnerCreateReservationComponent implements OnInit {
  @Input() cottageOwner: any;
  clients: Client[] = [];
  cottages: any = [];
  validForm: boolean = true;
  reservation: NewReservation = new NewReservation();
  startDate: Date = new Date();
  endDate: Date = new Date();
  utilities: Utility[] = [];

  constructor(private clientService: ClientService, private cottageService: CottageService) { }

  ngOnInit(): void {
    this.cottageService.findByCottageOwnerId(this.cottageOwner.id).subscribe((data: any) => this.cottages = data);
    this.clientService.getClientsWithCottageReservationAtTheMoment(this.cottageOwner.id).subscribe(data => {
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
    this.cottageService.createCottageReservation(this.reservation).subscribe(data => {
      if (data == -1) {
        alert('Reservation in selected date already exists or cottage is not available');
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

  cottageChosen(event: any) {
    this.cottageService.getCottageUtilities(this.reservation.entityId).subscribe(data => {
      this.reservation.utilities.length = 0;
      this.reservation.utilities = data;
    })
  }

}
