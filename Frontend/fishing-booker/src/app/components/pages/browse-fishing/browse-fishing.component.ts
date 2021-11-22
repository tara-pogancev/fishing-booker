import { Component, OnInit } from '@angular/core';
import { Adventure } from 'src/app/model/adventure-model';
import { SearchFilter } from 'src/app/model/search-filter-model';
import { AdvetnureService } from 'src/app/service/adventure-service';
import { SearchService } from 'src/app/service/search-service';

@Component({
  selector: 'app-browse-fishing',
  templateUrl: './browse-fishing.component.html',
  styleUrls: ['./browse-fishing.component.css'],
})
export class BrowseFishingComponent implements OnInit {
  adventures: Adventure[] = [];
  adventuresAll: Adventure[] = [];

  constructor(
    private adventureService: AdvetnureService,
    private searchService: SearchService
  ) {}

  ngOnInit(): void {
    this.adventureService.findAll().subscribe((data) => {
      this.adventuresAll = data;
      this.adventures = data;
    });
  }

  search(filter: SearchFilter) {
    this.adventures = this.searchService.filter(this.adventuresAll, filter)!;
  }
}
