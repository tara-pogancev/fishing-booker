import { Component, Input, OnInit } from '@angular/core';
import { Utility } from 'src/app/model/utility-model';
import { BoatService } from 'src/app/service/boat.service';
import { ImageService } from 'src/app/service/image.service';
import { BoatOwnerBoatsComponent } from '../boat-owner-boats/boat-owner-boats.component';

@Component({
  selector: 'app-boat-owner-boat-card',
  templateUrl: './boat-owner-boat-card.component.html',
  styleUrls: ['./boat-owner-boat-card.component.css']
})
export class BoatOwnerBoatCardComponent implements OnInit {
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

  constructor(private imageService: ImageService, private boatService: BoatService, private boats: BoatOwnerBoatsComponent) { }

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

  deleteBoat(id: number) {
    let canDelete = true;
    this.boatService.deleteBoat(id).subscribe(
      data => {
        canDelete = data
        if (canDelete == true) {
          const index = this.boats.boats.findIndex(boat => boat.id == id);
          this.boats.boats.splice(index, 1);
        } else {
          alert('You can not delete reserved boat');
        }
      });
  }

}
