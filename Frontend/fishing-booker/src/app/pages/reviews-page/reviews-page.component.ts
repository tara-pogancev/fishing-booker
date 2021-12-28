import { Component, OnInit } from '@angular/core';
import { ReviewModel } from 'src/app/model/review-model';
import { ReviewService } from 'src/app/service/review.service';

@Component({
  selector: 'app-reviews-page',
  templateUrl: './reviews-page.component.html',
  styleUrls: ['./reviews-page.component.css']
})
export class ReviewsPageComponent implements OnInit {
  reviews: ReviewModel[] = [];

  constructor(private reviewService: ReviewService) { }

  ngOnInit(): void {
    this.reviewService.getAllReviews().subscribe((data) => {
      this.reviews = data;
    });

  }

}
