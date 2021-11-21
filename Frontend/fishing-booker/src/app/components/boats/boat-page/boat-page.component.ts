import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Boat } from 'src/app/model/boat-model';
import { BoatService } from 'src/app/service/boat.service';
import { ImageService } from 'src/app/service/image.service';

@Component({
  selector: 'app-boat-page',
  templateUrl: './boat-page.component.html',
  styleUrls: ['./boat-page.component.css'],
})
export class BoatPageComponent implements OnInit {
  id: number = 0;
  boat: Boat = new Boat();
  navEquipment: string = '';
  image: any = 'assets/images/placeholder.jpg';

  constructor(
    private route: ActivatedRoute,
    private boatService: BoatService,
    private imageService: ImageService
  ) {}

  ngOnInit(): void {
    this.id = +this.route.snapshot.paramMap.get('id')!;

    this.boatService.findById(this.id).subscribe((data) => {
      this.boat = data;

      this.navEquipment = '';
      this.boat.navigationalEquipments.forEach((equipment) => {
        this.navEquipment += equipment + ', ';
      });
      this.navEquipment = this.navEquipment.slice(0, -2);

      if (this.boat.imageIds.length != 0) {
        this.getImageFromService(this.boat.imageIds[0]);
      } else {
        this.image = 'assets/images/placeholder.jpg';
      }
    });
  }

  createImageFromBlob(image: Blob) {
    let reader = new FileReader();
    reader.addEventListener(
      'load',
      () => {
        this.image = reader.result;
      },
      false
    );

    if (image) {
      reader.readAsDataURL(image);
    }
  }

  getImageFromService(id: number) {
    this.imageService.getImage(id).subscribe((data) => {
      this.createImageFromBlob(data);
    });
  }
}
