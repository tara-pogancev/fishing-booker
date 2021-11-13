import { Component, Input, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';

@Component({
  selector: 'client-boat-reservations',
  templateUrl: './boat-reservations.component.html',
  styleUrls: ['./boat-reservations.component.css'],
})
export class BoatReservationsComponent implements OnInit {
  @Input() user: Client = new Client();

  constructor() {}

  ngOnInit(): void {}
}
