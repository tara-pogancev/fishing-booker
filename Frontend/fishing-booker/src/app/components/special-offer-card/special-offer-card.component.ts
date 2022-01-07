import { Component, Input, OnInit } from '@angular/core';
import { Action } from 'rxjs/internal/scheduler/Action';
import { ActionModel } from 'src/app/model/action-model';
import { ImageService } from 'src/app/service/image.service';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'special-offer-card',
  templateUrl: './special-offer-card.component.html',
  styleUrls: ['./special-offer-card.component.css'],
})
export class SpecialOfferCardComponent implements OnInit {
  @Input() action: ActionModel = new ActionModel();
  image: any;
  daysLeft: number;

  constructor(
    private imageService: ImageService,
    private loginService: LoginService
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

  browse() {
    let currentUser = this.loginService.getCurrentUser();
    if (currentUser.role == 'LOGGED_OUT') {
      window.location.href = '/login';
    } else if (currentUser.role != 'REGISTERED_CLIENT') {
      alert('You must be logged in as a client to preview this page!');
    } else {
      window.location.href = '/client-db/SPECIAL_OFFERS';
    }
  }
}
