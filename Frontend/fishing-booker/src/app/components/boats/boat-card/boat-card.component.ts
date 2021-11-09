import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'boat-card',
  templateUrl: './boat-card.component.html',
  styleUrls: ['./boat-card.component.css'],
})
export class BoatCardComponent implements OnInit {
  @Input() id: string = '';
  @Input() title: string = 'Title';
  @Input() owner: string = 'John Smith';
  @Input() img: string = '/assets/images/boat.jpg';
  @Input() description: string = 'Description goes here';

  constructor() {}

  ngOnInit(): void {}

  readMore() {
    alert('New window');
  }
}
