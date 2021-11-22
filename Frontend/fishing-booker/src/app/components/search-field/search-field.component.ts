import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { SearchFilter } from 'src/app/model/search-filter-model';

@Component({
  selector: 'search-field',
  templateUrl: './search-field.component.html',
  styleUrls: ['./search-field.component.css'],
})
export class SearchFieldComponent implements OnInit {
  @Output() doSearch: EventEmitter<SearchFilter> = new EventEmitter();
  searchFilter: SearchFilter = new SearchFilter();
  pet: boolean = false;
  drinks: boolean = false;
  wifi: boolean = false;
  medic: boolean = false;
  exclusive: boolean = false;

  constructor() {}

  ngOnInit(): void {}

  togglePet() {
    this.pet = !this.pet;
  }

  toggleDrinks() {
    this.drinks = !this.drinks;
  }

  toggleWifi() {
    this.wifi = !this.wifi;
  }

  toggleMedic() {
    this.medic = !this.medic;
  }

  toggleExclusive() {
    this.exclusive = !this.exclusive;
  }

  search() {
    this.searchFilter.tags = [];
    if (this.pet) {
      this.searchFilter.tags.push('Pet-Friendly');
    }
    if (this.medic) {
      this.searchFilter.tags.push('Health Support');
    }
    if (this.wifi) {
      this.searchFilter.tags.push('WiFi Included');
    }
    if (this.drinks) {
      this.searchFilter.tags.push('Free Drinks');
    }
    if (this.exclusive) {
      this.searchFilter.tags.push('Exclusive');
    }
    this.doSearch.emit(this.searchFilter);
  }
}
