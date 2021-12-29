import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Cottage } from 'src/app/model/cottage-model';
import { ReviewModel } from 'src/app/model/review-model';
import { CottageService } from 'src/app/service/cottage.service';
import { ImageService } from 'src/app/service/image.service';
import { SubscriptionService } from 'src/app/service/subscription-service';

@Component({
  selector: 'app-cottage-page',
  templateUrl: './cottage-page.component.html',
  styleUrls: ['./cottage-page.component.css'],
})
export class CottagePageComponent implements OnInit {
  id: number = 0;
  cottage: Cottage = new Cottage();
  image: any = 'assets/images/placeholder.jpg';
  reviews: ReviewModel[] = [];
  isSubscribed: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private cottageService: CottageService,
    private subscriptionService: SubscriptionService
  ) {}

  ngOnInit(): void {
    this.id = +this.route.snapshot.paramMap.get('id')!;

    this.cottageService.findById(this.id).subscribe((data) => {
      this.cottage = data;
    });

    this.cottageService.getReviews(this.id).subscribe((data) => {
      this.reviews = data;
    });

    this.subscriptionService.getCottageSubscriptions().subscribe((data) => {
      let cottages = data;
      for (let cottage of cottages) {
        if (cottage.id == this.cottage.id) {
          this.isSubscribed = true;
          break;
        }
      }
    });
  }

  subscribe() {
    this.subscriptionService
      .subscribe('cottage', this.cottage.id)
      .subscribe((data) => {
        this.isSubscribed = true;
      });
  }
}
