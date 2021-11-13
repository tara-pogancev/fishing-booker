import { Component, Input, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';

@Component({
  selector: 'client-fishing-reservations',
  templateUrl: './fishing-reservations.component.html',
  styleUrls: ['./fishing-reservations.component.css'],
})
export class FishingReservationsComponent implements OnInit {
  @Input() user: Client = new Client();

  constructor() {}

  ngOnInit(): void {}
}
