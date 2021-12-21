import { formatDate } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';
import { Cottage } from 'src/app/model/cottage-model';
import { EntityModel } from 'src/app/model/entity-moedl';
import { SearchFilter } from 'src/app/model/search-filter-model';
import { CottageService } from 'src/app/service/cottage.service';
import { SearchService } from 'src/app/service/search-service';

@Component({
  selector: 'client-cottage-reservations',
  templateUrl: './cottage-reservations.component.html',
  styleUrls: ['./cottage-reservations.component.css'],
})
export class CottageReservationsComponent implements OnInit {
  @Input() user: Client = new Client();
  cottages: Cottage[] = [];
  cottagesAll: Cottage[] = [];

  today = new Date();
  startDate: Date = new Date();
  endDate: Date = new Date();
  people: number = 2;

  constructor(
    private cottageService: CottageService,
    private searchService: SearchService
  ) {}

  ngOnInit(): void {
    this.cottageService.findAll().subscribe((data) => {
      this.cottages = data;
      this.cottagesAll = data;
    });
  }

  search(filter: SearchFilter) {
    this.people = filter.people;
    this.startDate = filter.startDate;
    this.endDate = filter.endDate;

    if (
      this.endDate < this.today ||
      this.startDate < this.today ||
      this.startDate > this.endDate
    ) {
      alert('Invalid date input!');
    } else {
      this.cottageService.getSearch(filter).subscribe((data) => {
        this.cottagesAll = data;
        this.cottages = this.searchService.filter(this.cottagesAll, filter)!;
      });
    }
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
