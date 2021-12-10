import { Component, OnInit, Input } from '@angular/core';
import { Utility } from 'src/app/model/utility-model';
import { CottageService } from 'src/app/service/cottage.service';
import { ImageService } from 'src/app/service/image.service';
import { CottageOwnerCottagesComponent } from '../cottage-owner-cottages/cottage-owner-cottages.component';

@Component({
  selector: 'app-cottage-owner-cottage-card',
  templateUrl: './cottage-owner-cottage-card.component.html',
  styleUrls: ['./cottage-owner-cottage-card.component.css']
})
export class CottageOwnerCottageCardComponent implements OnInit {
  @Input() name: string = '';
  @Input() description: string = '';
  @Input() address: string = '';
  @Input() price: number = 0;
  @Input() rating: number = 0;
  @Input() ownerName: string = '';
  @Input() utilities: Utility[] = [];
  @Input() imageUrls: string[] = [];
  image: any;

  @Input() id: number = 0;
  @Input() type: string = 'entity';

  constructor(private imageService: ImageService, private cottageService: CottageService, private cottages: CottageOwnerCottagesComponent) { }

  ngOnInit(): void {
    if (this.imageUrls.length != 0) {
      this.image = this.imageUrls[0];
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

  deleteCottage(id: number) {
    let canDelete = true;
    this.cottageService.deleteCottage(id).subscribe(
      data => {
        canDelete = data
        if (canDelete == true) {
          const index = this.cottages.cottages.findIndex(cottage => cottage.id == id);
          this.cottages.cottages.splice(index, 1);
        } else {
          alert('You can not delete reserved cottage');
        }
      });
  }


}
