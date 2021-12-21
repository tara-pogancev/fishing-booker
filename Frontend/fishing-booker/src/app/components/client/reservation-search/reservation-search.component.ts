import { DatePipe } from '@angular/common';
import { i18nMetaToJSDoc } from '@angular/compiler/src/render3/view/i18n/meta';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { SearchFilter } from 'src/app/model/search-filter-model';
import { AddressService } from 'src/app/service/address.service';

@Component({
  selector: 'reservation-search',
  templateUrl: './reservation-search.component.html',
  styleUrls: ['./reservation-search.component.css'],
})
export class ReservationSearchComponent implements OnInit {
  @Output() doSearch: EventEmitter<SearchFilter> = new EventEmitter();
  countries = [];
  searchFilter = new SearchFilter();

  constructor(
    private addressService: AddressService
  ) {}

  ngOnInit(): void {
    this.addressService.getCountries().subscribe((data) => {
      this.countries = data;
    });
  }

  search(filter: SearchFilter) {
    this.searchFilter.tags = filter.tags;
    this.searchFilter.sort = filter.sort;
    this.searchFilter.text = filter.text;
    this.doSearch.emit(this.searchFilter);
  }

}
