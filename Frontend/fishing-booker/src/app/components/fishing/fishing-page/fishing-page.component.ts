import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Adventure } from 'src/app/model/adventure-model';
import { ReviewModel } from 'src/app/model/review-model';
import { AdvetnureService } from 'src/app/service/adventure-service';
import { ImageService } from 'src/app/service/image.service';
import { ReviewService } from 'src/app/service/review.service';
import { SubscriptionService } from 'src/app/service/subscription-service';

@Component({
  selector: 'app-fishing-page',
  templateUrl: './fishing-page.component.html',
  styleUrls: ['./fishing-page.component.css'],
})
export class FishingPageComponent implements OnInit {
  id: number = 0;
  adventure: Adventure = new Adventure();
  navEquipment: string = '';
  image: any = 'assets/images/placeholder.jpg';
  reviews: ReviewModel[] = [];
  isSubscribed: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private adventureService: AdvetnureService,
    private subscriptionService: SubscriptionService
  ) {}

  ngOnInit(): void {
    this.id = +this.route.snapshot.paramMap.get('id')!;

    this.adventureService.findById(this.id).subscribe((data) => {
      this.adventure = data;

      this.navEquipment = '';
      this.adventure.navigationalEquipments.forEach((equipment) => {
        this.navEquipment += equipment + ', ';
      });
      this.navEquipment = this.navEquipment.slice(0, -2);
      if (this.navEquipment == '') this.navEquipment = 'None';
    });

    this.adventureService.getReviews(this.id).subscribe((data) => {
      this.reviews = data;
    });

    this.subscriptionService.getInstructorsSubscriptions().subscribe((data) => {
      let instructors = data;
      for (let instructor of instructors) {
        if (instructor.id == this.adventure.ownerId) {
          this.isSubscribed = true;
          break;
        }
      }
    });
  }

  subscribe() {
    this.subscriptionService
      .subscribe('adventure', this.adventure.ownerId)
      .subscribe((data) => {
        this.isSubscribed = true;
      });
  }
}
