import { Component, OnInit } from '@angular/core';
import { Action } from 'rxjs/internal/scheduler/Action';
import { ActionModel } from 'src/app/model/action-model';
import { SearchFilter } from 'src/app/model/search-filter-model';
import { ActionSearchService } from 'src/app/service/action-search.service';
import { ActionService } from 'src/app/service/action.service';
import { SearchService } from 'src/app/service/search-service';

@Component({
  selector: 'app-browse-special-offers',
  templateUrl: './browse-special-offers.component.html',
  styleUrls: ['./browse-special-offers.component.css'],
})
export class BrowseSpecialOffersComponent implements OnInit {
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
