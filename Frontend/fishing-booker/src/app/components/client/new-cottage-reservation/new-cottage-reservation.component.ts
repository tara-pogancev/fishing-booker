import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Client } from 'src/app/model/client-model';
import { Cottage } from 'src/app/model/cottage-model';
import { ReservationModel } from 'src/app/model/reservation-model';
import { Utility } from 'src/app/model/utility-model';
import { ClientService } from 'src/app/service/client.service';
import { CottageService } from 'src/app/service/cottage.service';

@Component({
  selector: 'app-new-cottage-reservation',
  templateUrl: './new-cottage-reservation.component.html',
  styleUrls: ['./new-cottage-reservation.component.css'],
})
export class NewCottageReservationComponent implements OnInit {
  reservation: ReservationModel = new ReservationModel();
  client: Client = new Client();
  cottage: Cottage = new Cottage();

  constructor(
    private route: ActivatedRoute,
    private clientService: ClientService,
    private cottageService: CottageService
  ) {}

  ngOnInit(): void {
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

    this.cottageService.findById(this.reservation.entityId).subscribe(
      (data) => {
        this.cottage = data;
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
        this.reservation.price = this.cottage.price * days;
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
    this.cottageService.bookCottage(this.reservation).subscribe((data) => {
      window.location.href = '/client-db/UPCOMING';
      alert('Thank you! Your reservation is booked.');
    });
  }

  cancelReservation() {
    window.location.href = '/client-db/COTTAGE_RESERVATIONS';
  }
}
