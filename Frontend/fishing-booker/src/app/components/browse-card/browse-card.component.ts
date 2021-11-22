import { Component, Input, OnInit } from '@angular/core';
import { Utility } from 'src/app/model/utility-model';
import { ImageService } from 'src/app/service/image.service';

@Component({
  selector: 'browse-card',
  templateUrl: './browse-card.component.html',
  styleUrls: ['./browse-card.component.css'],
})
export class BrowseCardComponent implements OnInit {
  @Input() name: string = '';
  @Input() description: string = '';
  @Input() address: string = '';
  @Input() price: number = 0;
  @Input() rating: number = 0;
  @Input() ownerName: string = '';
  @Input() utilities: Utility[] = [];
  @Input() imageIds: number[] = [];
  image: any;

  @Input() id: number = 0;
  @Input() type: string = 'entity';

  constructor(private imageService: ImageService) {}

  ngOnInit(): void {
    if (this.imageIds.length != 0) {
      this.getImageFromService(this.imageIds[0]);
    } else {
      this.image = 'assets/images/placeholder.jpg';
    }
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
