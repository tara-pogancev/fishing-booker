import { Component, OnInit } from '@angular/core';
import { Boat } from 'src/app/model/boat-model';
import { BoatService } from 'src/app/service/boat.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  boats: Boat[] = [];
 
  constructor(private boatService: BoatService) {}

  ngOnInit(): void {
    this.boatService.findAll().subscribe((data) => {
      this.boats = data;
    });
  }
}
