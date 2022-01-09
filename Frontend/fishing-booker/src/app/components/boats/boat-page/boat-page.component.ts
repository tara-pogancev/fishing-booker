import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Boat } from 'src/app/model/boat-model';
import { ReservationModel } from 'src/app/model/reservation-model';
import { ReviewModel } from 'src/app/model/review-model';
import { BoatService } from 'src/app/service/boat.service';
import { ImageService } from 'src/app/service/image.service';
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

  constructor(
    private route: ActivatedRoute,
    private boatService: BoatService,
    private subscriptionService: SubscriptionService
  ) {}

  ngOnInit(): void {
    this.id = +this.route.snapshot.paramMap.get('id')!;

    this.boatService.findById(this.id).subscribe((data) => {
      this.boat = data;

      this.navEquipment = '';
      this.boat.navigationalEquipments.forEach((equipment) => {
        this.navEquipment += equipment + ', ';
      });
      this.navEquipment = this.navEquipment.slice(0, -2);
      if (this.navEquipment == '') this.navEquipment = 'None';

      console.log(data);
    });

    this.boatService.getBoatReservations(this.id).subscribe((data) => {
      this.reservations = data;
      this.reservationsLoaded = true;
    })

    this.boatService.getReviews(this.id).subscribe((data) => {
      this.reviews = data;
    });

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

  subscribe() {
    this.subscriptionService
      .subscribe('boat', this.boat.id)
      .subscribe((data) => {
        this.isSubscribed = true;
      });
  }
}
