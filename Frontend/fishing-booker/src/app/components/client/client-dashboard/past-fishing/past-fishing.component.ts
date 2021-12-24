import { Component, Input, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';
import { ReservationModel } from 'src/app/model/reservation-model';
import { ClientService } from 'src/app/service/client.service';

@Component({
  selector: 'client-past-fishing',
  templateUrl: './past-fishing.component.html',
  styleUrls: ['./past-fishing.component.css'],
})
export class PastFishingComponent implements OnInit {
  @Input() user: Client = new Client();
  reservations: ReservationModel[] = [];

  constructor(private clientService: ClientService) {}

  ngOnInit(): void {
    this.clientService.getClientPastAdventureReservations().subscribe((data) => {
      this.reservations = data;
    });
  }
}
