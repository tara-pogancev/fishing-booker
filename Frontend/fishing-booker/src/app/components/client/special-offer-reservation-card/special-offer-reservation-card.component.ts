import { Component, Input, OnInit } from '@angular/core';
import { AsyncAction } from 'rxjs/internal/scheduler/AsyncAction';
import { ActionModel } from 'src/app/model/action-model';
import { ActionService } from 'src/app/service/action.service';
import { ImageService } from 'src/app/service/image.service';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'special-offer-reservation-card',
  templateUrl: './special-offer-reservation-card.component.html',
  styleUrls: ['./special-offer-reservation-card.component.css'],
})
export class SpecialOfferReservationCardComponent implements OnInit {
  @Input() action: ActionModel = new ActionModel();
  image: any;
  daysLeft: number;

  constructor(
    private imageService: ImageService,
    private actionService: ActionService
  ) {}

  ngOnInit(): void {
    if (this.action.imageUrls.length != 0) {
      this.image = this.action.imageUrls[0];
    } else {
      this.image = 'assets/images/placeholder.jpg';
    }

    let today = new Date();
    this.action.startDate = new Date(this.action.startDate);
    this.daysLeft =
      1 +
      Math.floor(
        (this.action.startDate.getTime() - today.getTime()) /
          1000 /
          60 /
          60 /
          24
      );
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

  book() {
    if (
      confirm(
        'Are you sure you want to book the Special Offer for ' +
          this.action.entityName
      )
    ) {
      this.actionService.bookAction(this.action).subscribe((data) => {
        window.location.href = 'client-db/UPCOMING';
        alert('Special Offer succesfully booked!');
      });
    }
  }
}
