import { Component, OnInit } from '@angular/core';
import { ReviewModel } from 'src/app/model/review-model';
import { ReviewService } from 'src/app/service/review.service';

@Component({
  selector: 'app-admin-reviews',
  templateUrl: './admin-reviews.component.html',
  styleUrls: ['./admin-reviews.component.css'],
})
export class AdminReviewsComponent implements OnInit {
  reviews: ReviewModel[] = [];

  constructor(private reviewService: ReviewService) {}

  ngOnInit(): void {
    this.reviewService.getAllReviewsAdmin().subscribe((data) => {
      this.reviews = data;
    });
  }
}
