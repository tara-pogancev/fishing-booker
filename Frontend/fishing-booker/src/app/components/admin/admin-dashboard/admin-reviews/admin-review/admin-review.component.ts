import { Component, Input, OnInit } from '@angular/core';
import { ReviewService } from 'src/app/service/review.service';

@Component({
  selector: 'app-admin-review',
  templateUrl: './admin-review.component.html',
  styleUrls: ['./admin-review.component.css'],
})
export class AdminReviewComponent implements OnInit {
  @Input() review: any;
  imagePath: String = '';

  constructor(private reviewService: ReviewService) {}

  ngOnInit(): void {
    this.imagePath = 'assets/icons/' + this.review.reservationType + '.png';
  }

  numSequence(n: number): Array<number> {
    return Array(n);
  }

  accept() {
    this.reviewService.acceptReview(this.review.id).subscribe((data) => {
      this.review.approval = 'APPROVED';
    });
  }

  decline() {
    this.reviewService.declineReview(this.review.id).subscribe((data) => {
      this.review.approval = 'DECLINED';
    });
  }
}
