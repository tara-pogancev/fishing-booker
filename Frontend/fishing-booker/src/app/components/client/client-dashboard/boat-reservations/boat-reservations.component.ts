import { formatDate } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { fil } from 'date-fns/locale';
import { Boat } from 'src/app/model/boat-model';
import { Client } from 'src/app/model/client-model';
import { EntityModel } from 'src/app/model/entity-moedl';
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

  startDate: Date = new Date();
  endDate: Date = new Date();
  people: number = 2;

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
    console.log(filter)
    this.people = filter.people;
    this.startDate = filter.startDate;
    this.endDate = filter.endDate;
    this.boats = this.searchService.filter(this.boatsAll, filter)!;
  }

  newReservation(entity: EntityModel) {
    let startDate = formatDate(this.startDate, 'yyyy-MM-dd', 'en_US');
    let endDate = formatDate(this.endDate, 'yyyy-MM-dd', 'en_US');
    window.location.href =
      '/resertvation/new/' +
      entity.type +
      '/' +
      entity.id +
      '/' +
      startDate +
      '/' +
      endDate +
      '/' +
      this.people;
  }
}
