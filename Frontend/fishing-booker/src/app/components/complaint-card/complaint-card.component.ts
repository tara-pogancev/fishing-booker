import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'complaint-card',
  templateUrl: './complaint-card.component.html',
  styleUrls: ['./complaint-card.component.css'],
})
export class ComplaintCardComponent implements OnInit {
  @Input() review: any;
  imagePath: String = '';

  constructor() {}

  ngOnInit(): void {
    this.imagePath = 'assets/icons/' + this.review.reservationType + '.png';
  }
}
