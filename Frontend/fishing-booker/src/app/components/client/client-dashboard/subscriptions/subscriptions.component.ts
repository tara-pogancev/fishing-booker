import { Component, Input, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';

@Component({
  selector: 'app-subscriptions',
  templateUrl: './subscriptions.component.html',
  styleUrls: ['./subscriptions.component.css'],
})
export class SubscriptionsComponent implements OnInit {
  @Input() user: Client = new Client();
  hideNotification: boolean = false;

  constructor() {}

  ngOnInit(): void {}

  closeNotification() {
    this.hideNotification = true;
  }
}
