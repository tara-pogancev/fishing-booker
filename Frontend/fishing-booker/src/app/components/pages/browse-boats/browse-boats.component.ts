import { Component, OnInit } from '@angular/core';
import { Boat } from 'src/app/model/boat-model';
import { BoatService } from 'src/app/service/boat.service';

@Component({
  selector: 'app-browse-boats',
  templateUrl: './browse-boats.component.html',
  styleUrls: ['./browse-boats.component.css']
})
export class BrowseBoatsComponent implements OnInit {
  boats: Boat[] = [];

  constructor(private boatService: BoatService) { }

  ngOnInit(): void {
    this.boatService.findAll().subscribe((data) => {
      this.boats = data;
    });
  }

}
