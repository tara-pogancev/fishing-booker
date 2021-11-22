import { Component, Input, OnInit } from '@angular/core';
import { Adventure } from 'src/app/model/adventure-model';
import { Client } from 'src/app/model/client-model';
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

  constructor(
    private adventureService: AdvetnureService,
    private searchService: SearchService
  ) {}

  ngOnInit(): void {
    this.adventureService.findAll().subscribe((data) => {
      this.adventures = data;
      this.adventuresAll = data;
    });
  }

  search(filter: SearchFilter) {
    this.adventures = this.searchService.filter(this.adventuresAll, filter)!;
  }
}
