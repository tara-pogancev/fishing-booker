import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'cottage-card',
  templateUrl: './cottage-card.component.html',
  styleUrls: ['./cottage-card.component.css'],
})
export class CottageCardComponent implements OnInit {
  @Input() id: string = '';
  @Input() title: string = 'Title';
  @Input() owner: string = 'John Smith';
  @Input() img: string = '/assets/images/cottage-interior/interior1.jpeg';
  @Input() description: string = 'Description goes here';

  constructor() {}

  ngOnInit(): void {}

  readMore() {
    alert('New window');
  }
}
