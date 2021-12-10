import { Component, OnInit, Input } from '@angular/core';
import { Cottage } from 'src/app/model/cottage-model';
import { SearchFilter } from 'src/app/model/search-filter-model';
import { CottageService } from 'src/app/service/cottage.service';
import { SearchService } from 'src/app/service/search-service';
import { CottageOwnerDashboardComponent } from '../cottage-owner-dashboard.component';

@Component({
  selector: 'app-cottage-owner-cottages',
  templateUrl: './cottage-owner-cottages.component.html',
  styleUrls: ['./cottage-owner-cottages.component.css']
})
export class CottageOwnerCottagesComponent implements OnInit {
  @Input() cottageOwner: any;
  cottages: Cottage[] = [];
  cottagesAll: Cottage[] = [];
  text:string = '';

  constructor(private cottageService: CottageService, private searchService: SearchService, 
    private cottageOwnerDashboard: CottageOwnerDashboardComponent) { }

  ngOnInit(): void {
    this.cottageService.findByCottageOwnerId(this.cottageOwner.id).subscribe((data) => {
      this.cottages = data;
      this.cottagesAll = data;
    });
  }

  search() {
    let searchFilter=new SearchFilter();
    searchFilter.text=this.text;
    this.cottages = this.searchService.filterAdventures(this.cottagesAll, searchFilter)!;
  }

  addCottage() {
    this.cottageOwnerDashboard.changeTab('ADD_COTTAGE');
  }

}
