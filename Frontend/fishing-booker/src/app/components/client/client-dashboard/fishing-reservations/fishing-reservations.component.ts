import { formatDate } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { Adventure } from 'src/app/model/adventure-model';
import { Client } from 'src/app/model/client-model';
import { EntityModel } from 'src/app/model/entity-model';
import { SearchFilter } from 'src/app/model/search-filter-model';
import { AdvetnureService } from 'src/app/service/adventure-service';
import { SearchService } from 'src/app/service/search-service';

@Component({
  selector: 'client-fishing-reservations',
  templateUrl: './fishing-reservations.component.html',
  styleUrls: ['./fishing-reservations.component.css'],
})
export class FishingReservationsComponent implements OnInit {
  @Input() user: Client = new Client();
  adventures: Adventure[] = [];
  adventuresAll: Adventure[] = [];

  today = new Date();
  startDate: Date = new Date();
  endDate: Date = new Date();
  people: number = 2;

  constructor(
    private adventureService: AdvetnureService,
    private searchService: SearchService
  ) {}

  ngOnInit(): void {
    let filter = new SearchFilter();
    this.adventureService.getSearch(filter).subscribe((data) => {
      this.adventuresAll = data;
      this.adventures = this.searchService.filter(this.adventuresAll, filter)!;
    });
  }

  search(filter: SearchFilter) {
    this.people = filter.people;
    this.startDate = filter.startDate;
    this.endDate = filter.endDate;

    console.log(this.endDate);

    if (
      this.endDate <= this.today ||
      this.startDate <= this.today ||
      this.startDate > this.endDate
    ) {
      alert('Invalid date input!');
    } else {
      this.adventureService.getSearch(filter).subscribe((data) => {
        this.adventuresAll = data;
        this.adventures = this.searchService.filter(
          this.adventuresAll,
          filter
        )!;
      });
    }
  }

  newReservation(entity: EntityModel) {
    let startDate = this.startDate.toString();
    let endDate = this.endDate.toString();
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

  previewEntity(entity: EntityModel) {
    window.location.href = '/' + entity.type + '/' + entity.id;
  }
}
