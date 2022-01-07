import { Component, Input, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';
import { ReservationModel } from 'src/app/model/reservation-model';
import { ClientService } from 'src/app/service/client.service';
import { ReservationSortService } from 'src/app/service/reservation-sort.service';

@Component({
  selector: 'client-past-boat',
  templateUrl: './past-boat.component.html',
  styleUrls: ['./past-boat.component.css'],
})
export class PastBoatComponent implements OnInit {
  @Input() user: Client = new Client();
  reservations: ReservationModel[] = [];
  sort: string = 'NO_SORT';

  constructor(
    private clientService: ClientService,
    private reservationSortService: ReservationSortService
  ) {}

  ngOnInit(): void {
    this.clientService.getClientPastBoatReservations().subscribe((data) => {
      this.reservations = data;
    });
  }

  refreshSort() {
    this.reservations = this.reservationSortService.filter(
      this.reservations,
      this.sort
    );
  }
}
