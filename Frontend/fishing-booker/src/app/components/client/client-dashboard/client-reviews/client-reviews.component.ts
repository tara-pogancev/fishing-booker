import { Component, Input, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';
import { ReservationModel } from 'src/app/model/reservation-model';
import { ReviewModel } from 'src/app/model/review-model';
import { ReviewService } from 'src/app/service/review.service';

@Component({
  selector: 'client-reviews',
  templateUrl: './client-reviews.component.html',
  styleUrls: ['./client-reviews.component.css'],
})
export class ClientReviewsComponent implements OnInit {
  @Input() user: Client = new Client();

  reservations: ReservationModel[] = [];
  visibleMessage: boolean = false;
  visibleMessageBox: boolean = false;

  review: ReviewModel = new ReviewModel();

  constructor(private reviewService: ReviewService) {}

  ngOnInit(): void {
    this.reviewService.getClientAvailableReviews().subscribe((data) => {
      this.reservations = data;
    });
  }

  submit() {
    if (this.review.id == 0) {
      alert('Please select a reservation from the dropdown list!');
    } else if (this.review.review == '') {
      alert('Please enter your message!');
    } else {
      this.reviewService.newReview(this.review).subscribe((data) => {
        this.visibleMessage = true;
        this.visibleMessageBox = true;
      });
    }
  }

  hideMessage() {
    this.visibleMessageBox = false;
  }
}
