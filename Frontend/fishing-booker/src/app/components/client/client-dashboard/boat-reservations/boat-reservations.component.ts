import { Component, Input, OnInit } from '@angular/core';
import { Boat } from 'src/app/model/boat-model';
import { Client } from 'src/app/model/client-model';
import { SearchFilter } from 'src/app/model/search-filter-model';
import { BoatService } from 'src/app/service/boat.service';
import { SearchService } from 'src/app/service/search-service';

@Component({
  selector: 'client-boat-reservations',
  templateUrl: './boat-reservations.component.html',
  styleUrls: ['./boat-reservations.component.css'],
})
export class BoatReservationsComponent implements OnInit {
  @Input() user: Client = new Client();
  boats: Boat[] = [];
  boatsAll: Boat[] = [];

  constructor(
    private boatService: BoatService,
    private searchService: SearchService
  ) {}

  ngOnInit(): void {
    this.boatService.findAll().subscribe((data) => {
      this.boats = data;
      this.boatsAll = data;
    });
  }

  search(filter: SearchFilter) {
    this.boats = this.searchService.filter(this.boatsAll, filter)!;
  }
}
