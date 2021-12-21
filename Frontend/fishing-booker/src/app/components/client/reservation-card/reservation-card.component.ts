import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { EntityModel } from 'src/app/model/entity-moedl';
import { Utility } from 'src/app/model/utility-model';
import { ImageService } from 'src/app/service/image.service';

@Component({
  selector: 'reservation-card',
  templateUrl: './reservation-card.component.html',
  styleUrls: ['./reservation-card.component.css'],
})
export class ReservationCardComponent implements OnInit {
  @Output() doNewReservation: EventEmitter<EntityModel> = new EventEmitter();
  @Output() doPreviewEntity: EventEmitter<EntityModel> = new EventEmitter();

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

  constructor(private imageService: ImageService) {}

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

  newReservation() {
    let entity = new EntityModel(this.id, this.type);
    this.doNewReservation.emit(entity);
  }

  previewEntity() {
    let entity = new EntityModel(this.id, this.type);
    this.doPreviewEntity.emit(entity);
  }
}
