import { Component, Input, OnInit } from '@angular/core';
import { ReviewModel } from 'src/app/model/review-model';

@Component({
  selector: 'review-card',
  templateUrl: './review-card.component.html',
  styleUrls: ['./review-card.component.css'],
})
export class ReviewCardComponent implements OnInit {
  @Input() review: any;
  imagePath: String = '';

  constructor() {}

  ngOnInit(): void {
    this.imagePath = 'assets/icons/' + this.review.reservationType + '.png';
  }

  numSequence(n: number): Array<number> {
    return Array(n);
  }
}
