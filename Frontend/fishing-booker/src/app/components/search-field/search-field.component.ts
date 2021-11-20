import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'search-field',
  templateUrl: './search-field.component.html',
  styleUrls: ['./search-field.component.css'],
})
export class SearchFieldComponent implements OnInit {
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
}
