import { Component, Input, OnInit } from '@angular/core';
import { ActionModel } from 'src/app/model/action-model';
import { Client } from 'src/app/model/client-model';
import { SearchFilter } from 'src/app/model/search-filter-model';
import { ActionSearchService } from 'src/app/service/action-search.service';
import { ActionService } from 'src/app/service/action.service';

@Component({
  selector: 'client-special-offers',
  templateUrl: './special-offers.component.html',
  styleUrls: ['./special-offers.component.css'],
})
export class SpecialOffersComponent implements OnInit {
  @Input() user: Client = new Client();
  actions: ActionModel[] = [];
  actionsAll: ActionModel[] = [];

  constructor(
    private actionService: ActionService,
    private searchService: ActionSearchService
  ) {}

  ngOnInit(): void {
    this.actionService.findAll().subscribe((data) => {
      this.actionsAll = data;
      this.actions = data;
    });
  }

  search(filter: SearchFilter) {
    console.log(filter);
    this.actions = this.searchService.filter(this.actionsAll, filter)!;
  }
}
