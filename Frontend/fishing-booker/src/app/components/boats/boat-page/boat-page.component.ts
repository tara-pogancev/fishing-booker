import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Boat } from 'src/app/model/boat-model';
import { BoatService } from 'src/app/service/boat.service';

@Component({
  selector: 'app-boat-page',
  templateUrl: './boat-page.component.html',
  styleUrls: ['./boat-page.component.css'],
})
export class BoatPageComponent implements OnInit {
  id: number = 0;
  boat: Boat = new Boat();
  navEquipment: string = '';

  constructor(
    private route: ActivatedRoute,
    private boatService: BoatService
  ) {}

  ngOnInit(): void {
    this.id = +this.route.snapshot.paramMap.get('id')!;

    this.boatService.findById(this.id).subscribe((data) => {
      this.boat = data;
    });

    this.navEquipment = '';
    this.boat.navigationalEquipments.forEach((equipment) => {
      this.navEquipment += equipment + ', ';
    });
    this.navEquipment.slice(0, -2);
  }
}
