import { Component, Input, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';
import { ReservationModel } from 'src/app/model/reservation-model';
import { ClientService } from 'src/app/service/client.service';
import { ReservationSortService } from 'src/app/service/reservation-sort.service';

@Component({
  selector: 'client-upcoming-reservations',
  templateUrl: './upcoming-reservations.component.html',
  styleUrls: ['./upcoming-reservations.component.css'],
})
export class UpcomingReservationsComponent implements OnInit {
  @Input() user: Client = new Client();
  reservations: ReservationModel[] = [];
  response: ReservationModel[] = [];
  sort: string = 'NO_SORT';

  constructor(
    private clientService: ClientService,
    private reservationSortService: ReservationSortService
  ) {}

  ngOnInit(): void {
    this.clientService.getClientUpcomingReservations().subscribe((data) => {
      this.response = this.sortReservations(data);
      for (const res of this.response) {
        let today = new Date();
        res.startDate = new Date(res.startDate);
        console.log(res);
        let days =
          1 +
          Math.floor(
            (res.startDate.getTime() - today.getTime()) / 1000 / 60 / 60 / 24
          );
        res.canCancel = days > 3;
        this.reservations.push(res);
      }
    });
    console.log(this.reservations);
  }

  cancelReservation(entity: ReservationModel) {
    if (
      confirm(
        'Are you sure you want to cancel your reservation at ' +
          entity.entityName +
          '?'
      )
    ) {
      this.clientService.cancelReservation(entity.id).subscribe((data) => {
        window.location.href = '/client-db/UPCOMING';
        alert('Reservation canceled.');
      });
    }
  }

  sortReservations(entities: any[]) {
    return entities.sort((n1, n2) => {
      if (n1.startDate > n2.startDate) {
        return 1;
      } else {
        return -1;
      }
    });
  }

  refreshSort() {
    this.reservations = this.reservationSortService.filter(
      this.reservations,
      this.sort
    );
  }
}
