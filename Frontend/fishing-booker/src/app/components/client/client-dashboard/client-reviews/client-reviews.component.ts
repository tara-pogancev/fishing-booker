import { Component, Input, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';

@Component({
  selector: 'client-reviews',
  templateUrl: './client-reviews.component.html',
  styleUrls: ['./client-reviews.component.css'],
})
export class ClientReviewsComponent implements OnInit {
  @Input() user: Client = new Client();

  constructor() {}

  ngOnInit(): void {}
}
