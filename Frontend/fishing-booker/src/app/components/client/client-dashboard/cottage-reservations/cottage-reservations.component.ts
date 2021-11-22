import { Component, Input, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';
import { Cottage } from 'src/app/model/cottage-model';
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
    this.cottages = this.searchService.filter(this.cottagesAll, filter)!;
  }
}
