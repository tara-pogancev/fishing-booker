import { Component, Input, OnInit } from '@angular/core';
import { Boat } from 'src/app/model/boat-model';
import { Client } from 'src/app/model/client-model';
import { BoatService } from 'src/app/service/boat.service';

@Component({
  selector: 'client-boat-reservations',
  templateUrl: './boat-reservations.component.html',
  styleUrls: ['./boat-reservations.component.css'],
})
export class BoatReservationsComponent implements OnInit {
  @Input() user: Client = new Client();
  boats: Boat[] = [];

  constructor(private boatService: BoatService) {}

  ngOnInit(): void {
    this.boatService.findAll().subscribe((data) => {
      this.boats = data;
    });
  }
}
