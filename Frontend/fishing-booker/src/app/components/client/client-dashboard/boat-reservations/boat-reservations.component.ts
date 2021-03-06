import { Component, Input, OnInit } from '@angular/core';
import { Boat } from 'src/app/model/boat-model';
import { Client } from 'src/app/model/client-model';
import { EntityModel } from 'src/app/model/entity-model';
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

  today = new Date();
  startDate: Date = new Date();
  endDate: Date = new Date();
  people: number = 2;

  didSearch: boolean = false;
  datesOverlap: boolean = false;

  constructor(
    private boatService: BoatService,
    private searchService: SearchService
  ) {}

  ngOnInit(): void {}

  search(filter: SearchFilter) {
    this.people = filter.people;
    this.startDate = filter.startDate;
    this.endDate = filter.endDate;

    if (
      new Date(filter.startDate) <= this.today ||
      new Date(filter.endDate) <= this.today ||
      this.startDate > this.endDate ||
      !this.validateDate(this.startDate) ||
      !this.validateDate(this.endDate)
    ) {
      alert('Invalid date input!');
    } else {
      this.boatService.getSearch(filter).subscribe(
        (data) => {
          this.didSearch = true;
          this.datesOverlap = false;
          this.boatsAll = data;
          this.boats = this.searchService.filter(this.boatsAll, filter)!;
          console.log(data);
        },
        (err) => {
          if (err.status == 409) {
            this.datesOverlap = true;
            this.didSearch = true;
          }
        }
      );
    }
  }

  newReservation(entity: EntityModel) {
    if (this.didSearch == false) {
      alert('Please enter dates before booking!');
    } else {
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
  }

  previewEntity(entity: EntityModel) {
    window.location.href = '/' + entity.type + '/' + entity.id;
  }

  validateDate(date: Date) {
    let string = date.toString();
    return !(string === '' || string.indexOf(' ') >= 0);
  }
}
