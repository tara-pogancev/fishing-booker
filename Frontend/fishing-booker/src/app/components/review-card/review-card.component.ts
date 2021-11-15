import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'review-card',
  templateUrl: './review-card.component.html',
  styleUrls: ['./review-card.component.css'],
})
export class ReviewCardComponent implements OnInit {
  @Input() state: string = 'ACCEPTED';
  constructor() {}

  ngOnInit(): void {}
}
