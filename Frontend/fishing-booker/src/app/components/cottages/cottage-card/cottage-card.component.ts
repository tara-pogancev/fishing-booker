import { Component, OnInit, Input } from '@angular/core';
import { Cottage } from 'src/app/model/cottage-model';
import { CottageService } from 'src/app/service/cottage.service';
import { ImageService } from 'src/app/service/image.service';

@Component({
  selector: 'cottage-card',
  templateUrl: './cottage-card.component.html',
  styleUrls: ['./cottage-card.component.css'],
})
export class CottageCardComponent implements OnInit {
  @Input() id: number = 0;
  @Input() cottage: Cottage = new Cottage();
  image: any = 'assets/images/placeholder.jpg';

  constructor(
    private cottageService: CottageService,
    private imageService: ImageService
  ) {}

  ngOnInit(): void {
    this.cottageService.findById(this.id).subscribe((data) => {
      this.cottage = data;

      if (this.cottage.imageIds.length != 0) {
        this.getImageFromService(this.cottage.imageIds[0]);
      } else {
        this.image = 'assets/images/placeholder.jpg';
      }

      console.log(this.cottage);
    });
  }

  readMore() {
    window.location.href = 'cottage/' + this.id;
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
