import { Component, OnInit } from '@angular/core';
import { Boat } from 'src/app/model/boat-model';
import { SearchFilter } from 'src/app/model/search-filter-model';
import { BoatService } from 'src/app/service/boat.service';
import { SearchService } from 'src/app/service/search-service';

@Component({
  selector: 'app-browse-boats',
  templateUrl: './browse-boats.component.html',
  styleUrls: ['./browse-boats.component.css'],
})
export class BrowseBoatsComponent implements OnInit {
  boats: Boat[] = [];
  boatsAll: Boat[] = [];

  constructor(
    private boatService: BoatService,
    private searchService: SearchService
  ) {}

  ngOnInit(): void {
    this.boatService.findAll().subscribe((data) => {
      this.boatsAll = data;
      this.boats = data;
    });
  }

  search(filter: SearchFilter) {
    this.boats = this.searchService.filter(this.boatsAll, filter)!;
  }
}
