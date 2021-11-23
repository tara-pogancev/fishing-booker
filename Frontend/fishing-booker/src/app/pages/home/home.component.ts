import { Component, OnInit } from '@angular/core';
import { Adventure } from 'src/app/model/adventure-model';
import { Boat } from 'src/app/model/boat-model';
import { Cottage } from 'src/app/model/cottage-model';
import { AdvetnureService } from 'src/app/service/adventure-service';
import { BoatService } from 'src/app/service/boat.service';
import { CottageService } from 'src/app/service/cottage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  boats: Boat[] = [];
  cottages: Cottage[] = [];
  adventures: Adventure[] = [];

  constructor(
    private boatService: BoatService,
    private cottageService: CottageService,
    private adventureService: AdvetnureService
  ) {}

  ngOnInit(): void {
    this.boatService.findAll().subscribe((data) => {
      this.boats = data;
    });

    this.cottageService.findAll().subscribe((data) => {
      this.cottages = data;
    });

    this.adventureService.findAll().subscribe((data) => {
      this.adventures = data;
    });
  }
}
