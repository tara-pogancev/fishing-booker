import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Adventure } from 'src/app/model/adventure-model';
import { Client } from 'src/app/model/client-model';
import { ReservationModel } from 'src/app/model/reservation-model';
import { Utility } from 'src/app/model/utility-model';
import { AdvetnureService } from 'src/app/service/adventure-service';
import { ClientService } from 'src/app/service/client.service';

@Component({
  selector: 'app-new-adventure-reservation',
  templateUrl: './new-adventure-reservation.component.html',
  styleUrls: ['./new-adventure-reservation.component.css'],
})
export class NewAdventureReservationComponent implements OnInit {
  reservation: ReservationModel = new ReservationModel();
  client: Client = new Client();
  adventure: Adventure = new Adventure();
  loader: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private clientService: ClientService,
    private adventureService: AdvetnureService
  ) {}

  ngOnInit(): void {
    this.reservation.utilityIds = [];
    this.reservation.startDate = new Date(
      this.route.snapshot.paramMap.get('startDate')!
    );
    this.reservation.endDate = new Date(
      this.route.snapshot.paramMap.get('endDate')!
    );
    this.reservation.entityId = +this.route.snapshot.paramMap.get('id')!;
    this.reservation.entityType = this.route.snapshot.paramMap.get('type')!;
    this.reservation.people = +this.route.snapshot.paramMap.get('people')!;

    this.clientService.getCurrentClient().subscribe(
      (data: Client) => {
        this.client = data;
        this.reservation.userId = +this.client.id;
      },
      (err) => {
        if (err.status == 403 || err.status == 401) {
          window.location.href = '/login';
        }
      }
    );

    this.adventureService.findById(this.reservation.entityId).subscribe(
      (data) => {
        this.adventure = data;
        console.log(data);
        let days =
          1 +
          Math.floor(
            (this.reservation.endDate.getTime() -
              this.reservation.startDate.getTime()) /
              1000 /
              60 /
              60 /
              24
          );
        this.reservation.price = this.adventure.price * days;
      },
      (err) => {
        if (err.status == 404) {
          window.location.href = '/404';
        }
      }
    );
  }

  alterPrice(utility: Utility) {
    if (this.isUtilityIncluded(utility)) {
      this.reservation.price = this.reservation.price - utility.price;
      this.removeUtilityId(utility);
    } else {
      this.reservation.price = this.reservation.price + utility.price;
      this.reservation.utilityIds.push(utility.id);
    }
  }

  isUtilityIncluded(utility: Utility) {
    console.log(utility);
    for (let utilityId of this.reservation.utilityIds) {
      if (utilityId == utility.id) {
        return true;
      }
    }
    return false;
  }

  removeUtilityId(utility: Utility) {
    let utilityIdsNew = [];
    for (let utilityId of this.reservation.utilityIds) {
      if (utilityId != utility.id) {
        utilityIdsNew.push(utilityId);
      }
    }
    this.reservation.utilityIds = utilityIdsNew;
  }

  createReservation() {
    this.loader = true;
    this.adventureService.bookAdventure(this.reservation).subscribe(
      (data) => {
        this.loader = false;
        window.location.href = '/client-db/UPCOMING';
        alert('Thank you! Your reservation is booked.');
      },
      (err) => {
        window.location.href = '/error';
      }
    );
  }

  cancelReservation() {
    window.location.href = '/client-db/FISHING_RESERVATIONS';
  }
}
