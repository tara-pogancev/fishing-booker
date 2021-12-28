import { Component, Input, OnInit } from '@angular/core';
import { Boat } from 'src/app/model/boat-model';
import { Client } from 'src/app/model/client-model';
import { SubscriptionService } from 'src/app/service/subscription-service';

@Component({
  selector: 'app-subscriptions',
  templateUrl: './subscriptions.component.html',
  styleUrls: ['./subscriptions.component.css'],
})
export class SubscriptionsComponent implements OnInit {
  @Input() user: Client = new Client();
  hideNotification: boolean = false;

  subscriptionCount: number = 0;
  cottageSubCount: number = 0;
  boatSubCount: number = 0;
  adventureSubCount: number = 0;

  boats: Boat[] = [];

  constructor(private subscriptionService: SubscriptionService) {}

  ngOnInit(): void {
    this.subscriptionService.getBoatSubscriptions().subscribe((data) => {
      console.log(data);
      this.boats = data;
      this.boatSubCount = this.boats.length;
      this.subscriptionCount = this.subscriptionCount + this.boatSubCount;
    });
  }

  closeNotification() {
    this.hideNotification = true;
  }
}
