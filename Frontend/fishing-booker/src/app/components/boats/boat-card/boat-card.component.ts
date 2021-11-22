import { Component, Input, OnInit } from '@angular/core';
import { Boat } from 'src/app/model/boat-model';
import { BoatService } from 'src/app/service/boat.service';
import { ImageService } from 'src/app/service/image.service';

@Component({
  selector: 'boat-card',
  templateUrl: './boat-card.component.html',
  styleUrls: ['./boat-card.component.css'],
})
export class BoatCardComponent implements OnInit {
  @Input() id: number = 0;
  boat: Boat = new Boat();
  image: any = 'assets/images/placeholder.jpg';

  constructor(
    private boatService: BoatService,
    private imageService: ImageService
  ) {}

  ngOnInit(): void {
    this.boatService.findById(this.id).subscribe((data) => {
      this.boat = data;

      if (this.boat.imageIds.length != 0) {
        this.getImageFromService(this.boat.imageIds[0]);
      } else {
        this.image = 'assets/images/placeholder.jpg';
      }

      console.log(this.boat);
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

  readMore() {
    window.location.href = 'boat/' + this.id;
  }
}
