import { Component, Input, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';
import { ReviewModel } from 'src/app/model/review-model';
import { ReviewService } from 'src/app/service/review.service';

@Component({
  selector: 'my-reviews-and-complaints',
  templateUrl: './my-reviews-and-complaints.component.html',
  styleUrls: ['./my-reviews-and-complaints.component.css'],
})
export class MyReviewsAndComplaintsComponent implements OnInit {
  @Input() user: Client = new Client();
  reviews: ReviewModel[] = [];

  constructor(private reviewService: ReviewService) {}

  ngOnInit(): void {
    this.reviewService.getClientReviewsComplaints().subscribe((data) => {
      this.reviews = this.sortReviews(data);
    });
  }

  sortReviews(entities: any[]) {
    return entities.sort((n1, n2) => {
      if (n1.date < n2.date) {
        return 1;
      } else {
        return -1;
      }
    });
  }
}
