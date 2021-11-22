import { Component, Input, OnInit } from '@angular/core';
import { Adventure } from 'src/app/model/adventure-model';
import { AdvetnureService } from 'src/app/service/adventure-service';
import { ImageService } from 'src/app/service/image.service';

@Component({
  selector: 'adventure-card',
  templateUrl: './adventure-card.component.html',
  styleUrls: ['./adventure-card.component.css'],
})
export class AdventureCardComponent implements OnInit {
  @Input() id: number = 0;
  adventure: Adventure = new Adventure();
  image: any = 'assets/images/placeholder.jpg';

  constructor(
    private adventureService: AdvetnureService,
    private imageService: ImageService
  ) {}

  ngOnInit(): void {
    this.adventureService.findById(this.id).subscribe((data) => {
      this.adventure = data;

      if (this.adventure.imageIds.length != 0) {
        this.getImageFromService(this.adventure.imageIds[0]);
      } else {
        this.image = 'assets/images/placeholder.jpg';
      }

      console.log(this.adventure);
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
    window.location.href = 'adventure/' + this.id;
  }

}
