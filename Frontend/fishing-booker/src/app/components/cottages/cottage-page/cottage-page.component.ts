import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Cottage } from 'src/app/model/cottage-model';
import { CottageService } from 'src/app/service/cottage.service';
import { ImageService } from 'src/app/service/image.service';

@Component({
  selector: 'app-cottage-page',
  templateUrl: './cottage-page.component.html',
  styleUrls: ['./cottage-page.component.css'],
})
export class CottagePageComponent implements OnInit {
  id: number = 0;
  cottage: Cottage = new Cottage();
  image: any = 'assets/images/placeholder.jpg';

  constructor(
    private route: ActivatedRoute,
    private cottageService: CottageService,
    private imageService: ImageService
  ) {}

  ngOnInit(): void {
    this.id = +this.route.snapshot.paramMap.get('id')!;

    this.cottageService.findById(this.id).subscribe((data) => {
      this.cottage = data;

      if (this.cottage.imageIds.length != 0) {
        this.getImageFromService(this.cottage.imageIds[0]);
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
