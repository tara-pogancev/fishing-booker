import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Boat } from 'src/app/model/boat-model';
import { BoatService } from 'src/app/service/boat.service';
import { ImageService } from 'src/app/service/image.service';

@Component({
  selector: 'app-boat-owner-boat-preview',
  templateUrl: './boat-owner-boat-preview.component.html',
  styleUrls: ['./boat-owner-boat-preview.component.css']
})
export class BoatOwnerBoatPreviewComponent implements OnInit {
  id: number = 0;
  boat: Boat = new Boat();
  image: any = 'assets/images/placeholder.jpg';

  constructor(
    private route: ActivatedRoute,
    private boatService: BoatService,
    private imageService: ImageService
  ) { }

  ngOnInit(): void {
    this.id = +this.route.snapshot.paramMap.get('id')!;

    this.boatService.findById(this.id).subscribe((data) => {
      this.boat = data;
    });
  }

  editBoat() {
    location.href="edit-boat/"+this.id;
  }

  boatTypeToString(boatType: string) {
    let re = /\_/gi;
    boatType = boatType.replace(re, ' ');
    boatType = boatType.charAt(0).toUpperCase() + boatType.substring(1).toLowerCase();
    return boatType;
  }

  navigationalEquipmentToString(navigationalEquipment: string[]) {
    let navEquipment = '';
    navigationalEquipment.forEach((equipment) => {
      navEquipment += this.boatTypeToString(equipment) + ', ';
    });
    navEquipment = navEquipment.slice(0, -2);
    if (navEquipment == '') navEquipment = 'None';

    return navEquipment;
  }
}
