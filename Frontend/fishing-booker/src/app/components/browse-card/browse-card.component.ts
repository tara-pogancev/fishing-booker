import { Component, Input, OnInit } from '@angular/core';
import { Utility } from 'src/app/model/utility-model';

@Component({
  selector: 'browse-card',
  templateUrl: './browse-card.component.html',
  styleUrls: ['./browse-card.component.css'],
})
export class BrowseCardComponent implements OnInit {
  @Input() name: string = '';
  @Input() description: string = '';
  @Input() address: string = '';
  @Input() price: number = 0;
  @Input() rating: number = 0;
  @Input() ownerName: string = '';
  @Input() utilities: Utility[] = [];

  @Input() id: number = 0;
  @Input() type: string = 'entity';

  constructor() {}

  ngOnInit(): void {}

  readMore() {
    window.location.href = this.type + '/' + this.id;
  }
}
