
import { Component, Input, OnInit } from '@angular/core';
import { Boat } from 'src/app/model/boat-model';
import { SearchFilter } from 'src/app/model/search-filter-model';
import { BoatService } from 'src/app/service/boat.service';
import { SearchService } from 'src/app/service/search-service';
import { BoatOwnerDashboardComponent } from '../boat-owner-dashboard.component';

@Component({
  selector: 'app-boat-owner-boats',
  templateUrl: './boat-owner-boats.component.html',
  styleUrls: ['./boat-owner-boats.component.css']
})
export class BoatOwnerBoatsComponent implements OnInit {
  @Input() boatOwner: any;
  boats: Boat[] = [];
  boatsAll: Boat[] = [];
  text:string = '';

  constructor(private boatService: BoatService, private searchService: SearchService, 
    private boatOwnerDashboard: BoatOwnerDashboardComponent) { }

  ngOnInit(): void {
    this.boatService.findByBoatOwnerId(this.boatOwner.id).subscribe((data) => {
      this.boats = data;
      this.boatsAll = data;
    });
  }

  search() {
    let searchFilter=new SearchFilter();
    searchFilter.text=this.text;
    this.boats = this.searchService.filterAdventures(this.boatsAll, searchFilter)!;
  }

  addBoat() {
    this.boatOwnerDashboard.changeTab('ADD_BOAT');
  }

}
