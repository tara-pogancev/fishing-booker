import { Component, Input, OnInit } from '@angular/core';
import { fi } from 'date-fns/locale';
import { AsyncAction } from 'rxjs/internal/scheduler/AsyncAction';
import { ActionModel } from 'src/app/model/action-model';
import { SearchFilter } from 'src/app/model/search-filter-model';
import { ActionService } from 'src/app/service/action.service';
import { ClientService } from 'src/app/service/client.service';
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

  datesOverlap = false;
  didCancel = false;

  constructor(
    private imageService: ImageService,
    private actionService: ActionService,
    private clientService: ClientService
  ) {}

  ngOnInit(): void {
    let filter = new SearchFilter();
    filter.startDate = this.action.startDate;
    filter.endDate = this.action.endDate;

    this.clientService.clientDatesOverlap(filter).subscribe((data) => {
      this.datesOverlap = data;
    });

    this.clientService
      .isActionCanceledByClient(this.action.id)
      .subscribe((data) => {
        this.didCancel = data;
      });

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
      this.actionService.bookAction(this.action).subscribe(
        (data) => {
          window.location.href = 'client-db/UPCOMING';
          alert('Special Offer succesfully booked!');
        },
        (err) => {
          if (err.status == 409) {
            window.location.href = 'error';
          }
        }
      );
    }
  }
}
