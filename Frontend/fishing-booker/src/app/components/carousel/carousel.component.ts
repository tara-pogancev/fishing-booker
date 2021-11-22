import { Component, Input, OnInit } from '@angular/core';
import { ImageService } from 'src/app/service/image.service';

@Component({
  selector: 'image-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.css'],
})
export class CarouselComponent implements OnInit {
  @Input() imageIds: number[] = [];
  images: any = [];

  constructor(private imageService: ImageService) {}

  ngOnInit(): void {
    if (this.imageIds.length != 0) {
      for (let imageId of this.imageIds)
        this.getImageFromService(imageId);
    } else {
      this.images.push('assets/images/placeholder.jpg');
    }
  }

  createImageFromBlob(image: Blob) {
    let reader = new FileReader();
    reader.addEventListener(
      'load',
      () => {
        this.images.push(reader.result);
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
