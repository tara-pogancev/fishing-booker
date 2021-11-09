import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'adventure-card',
  templateUrl: './adventure-card.component.html',
  styleUrls: ['./adventure-card.component.css'],
})
export class AdventureCardComponent implements OnInit {
  @Input() id: string = '';
  @Input() title: string = 'Title';
  @Input() owner: string = 'John Smith';
  @Input() img: string = '/assets/images/fishing.jpg';
  @Input() description: string = 'Description goes here';

  constructor() {}

  ngOnInit(): void {}

  readMore() {
    alert('New window');
  }
}
