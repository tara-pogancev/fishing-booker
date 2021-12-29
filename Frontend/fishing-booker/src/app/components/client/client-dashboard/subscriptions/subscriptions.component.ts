import { Component, Input, OnInit } from '@angular/core';
import { Boat } from 'src/app/model/boat-model';
import { Client } from 'src/app/model/client-model';
import { Cottage } from 'src/app/model/cottage-model';
import { InstructorSubscriptionModel } from 'src/app/model/instructor-subscripton-model';
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
  cottages: Cottage[] = [];
  instructors: InstructorSubscriptionModel[] = [];

  constructor(private subscriptionService: SubscriptionService) {}

  ngOnInit(): void {
    this.subscriptionService.getBoatSubscriptions().subscribe((data) => {
      this.boats = data;
      this.boatSubCount = this.boats.length;
      this.subscriptionCount = this.subscriptionCount + this.boatSubCount;
    });

    this.subscriptionService.getCottageSubscriptions().subscribe((data) => {
      this.cottages = data;
      this.cottageSubCount = this.cottages.length;
      this.subscriptionCount = this.subscriptionCount + this.cottageSubCount;
    });

    this.subscriptionService.getInstructorsSubscriptions().subscribe((data) => {
      this.instructors = data;
      this.adventureSubCount = this.instructors.length;
      this.subscriptionCount = this.subscriptionCount + this.adventureSubCount;
    });
  }

  closeNotification() {
    this.hideNotification = true;
  }
}
