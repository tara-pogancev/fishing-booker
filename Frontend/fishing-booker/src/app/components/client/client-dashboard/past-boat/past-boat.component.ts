import { Component, Input, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';
import { ReservationModel } from 'src/app/model/reservation-model';
import { ClientService } from 'src/app/service/client.service';

@Component({
  selector: 'client-past-boat',
  templateUrl: './past-boat.component.html',
  styleUrls: ['./past-boat.component.css'],
})
export class PastBoatComponent implements OnInit {
  @Input() user: Client = new Client();
  reservations: ReservationModel[] = [];

  constructor(private clientService: ClientService) {}

  ngOnInit(): void {
    this.clientService.getClientPastBoatReservations().subscribe((data) => {
      this.reservations = data;
    });
  }
}
