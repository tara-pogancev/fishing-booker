import { Component, OnInit } from '@angular/core';
import { Cottage } from 'src/app/model/cottage-model';
import { SearchFilter } from 'src/app/model/search-filter-model';
import { CottageService } from 'src/app/service/cottage.service';
import { SearchService } from 'src/app/service/search-service';

@Component({
  selector: 'app-browse-cottages',
  templateUrl: './browse-cottages.component.html',
  styleUrls: ['./browse-cottages.component.css'],
})
export class BrowseCottagesComponent implements OnInit {
  cottagesAll: Cottage[] = [];
  cottages: Cottage[] = [];

  constructor(
    private cottageService: CottageService,
    private searchService: SearchService
  ) {}

  ngOnInit(): void {
    this.cottageService.findAll().subscribe((data) => {
      this.cottagesAll = data;
      this.cottages = data;
    });
  }

  search(filter: SearchFilter) {
    this.cottages = this.searchService.filter(this.cottagesAll, filter)!;
  }
}
