import { Component, Input, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';
import { ReservationModel } from 'src/app/model/reservation-model';
import { ClientService } from 'src/app/service/client.service';

@Component({
  selector: 'client-past-cottage',
  templateUrl: './past-cottage.component.html',
  styleUrls: ['./past-cottage.component.css'],
})
export class PastCottageComponent implements OnInit {
  @Input() user: Client = new Client();
  reservations: ReservationModel[] = [];

  constructor(private clientService: ClientService) {}

  ngOnInit(): void {
    this.clientService.getClientPastCottageReservations().subscribe((data) => {
      this.reservations = data;
    });
  }
}
