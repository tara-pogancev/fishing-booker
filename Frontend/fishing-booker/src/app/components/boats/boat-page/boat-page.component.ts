import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ActionModel } from 'src/app/model/action-model';
import { Boat } from 'src/app/model/boat-model';
import { ReservationModel } from 'src/app/model/reservation-model';
import { ReviewModel } from 'src/app/model/review-model';
import { ActionService } from 'src/app/service/action.service';
import { BoatService } from 'src/app/service/boat.service';
import { ClientService } from 'src/app/service/client.service';
import { ImageService } from 'src/app/service/image.service';
import { LoginService } from 'src/app/service/login.service';
import { SubscriptionService } from 'src/app/service/subscription-service';

@Component({
  selector: 'app-boat-page',
  templateUrl: './boat-page.component.html',
  styleUrls: ['./boat-page.component.css'],
})
export class BoatPageComponent implements OnInit {
  id: number = 0;
  boat: Boat = new Boat();
  reservations: ReservationModel[] = [];
  reservationsLoaded: boolean = false;
  navEquipment: string = '';
  image: any = 'assets/images/placeholder.jpg';
  reviews: ReviewModel[] = [];
  isSubscribed: boolean = false;
  actions: ActionModel[] = [];
  isClient: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private boatService: BoatService,
    private subscriptionService: SubscriptionService,
    private actionService: ActionService,
    private clientService: ClientService,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    this.id = +this.route.snapshot.paramMap.get('id')!;

    this.isClient = this.clientService.isCurrentUserClient();

    this.boatService.findById(this.id).subscribe((data) => {
      this.boat = data;

      this.navEquipment = '';
      this.boat.navigationalEquipments.forEach((equipment) => {
        this.navEquipment += equipment + ', ';
      });
      this.navEquipment = this.navEquipment.slice(0, -2);
      if (this.navEquipment == '') this.navEquipment = 'None';
    });

    this.boatService.getBoatReservations(this.id).subscribe((data) => {
      this.reservations = data;
      this.reservationsLoaded = true;
    });

    this.boatService.getReviews(this.id).subscribe((data) => {
      this.reviews = data;
    });

    if (this.isClient) {
      this.subscriptionService.getBoatSubscriptions().subscribe((data) => {
        let boats = data;
        for (let boat of boats) {
          if (boat.id == this.boat.id) {
            this.isSubscribed = true;
            break;
          }
        }
      });
    }

    this.actionService.findAll().subscribe((data) => {
      for (let action of data) {
        if (action.entityId == this.boat.id && action.entityType == 'BOAT') {
          this.actions.push(action);
        }
      }
    });
  }

  subscribe() {
    this.subscriptionService
      .subscribe('boat', this.boat.id)
      .subscribe((data) => {
        this.isSubscribed = true;
      });
  }

  bookNowButton() {
    if (this.isClient) {
      window.location.href = '/client-db/BOAT_RESERVATIONS';
    } else if (this.loginService.isUserLoggedIn()) {
      window.location.href = '/401';
    } else {
      window.location.href = '/login';
    }
  }
}
