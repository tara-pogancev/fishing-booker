import { Component, Input, OnInit } from '@angular/core';
import { SubscriptionService } from 'src/app/service/subscription-service';

@Component({
  selector: 'subscription-component',
  templateUrl: './subscription-component.component.html',
  styleUrls: ['./subscription-component.component.css'],
})
export class SubscriptionComponentComponent implements OnInit {
  @Input() entity: any;
  @Input() type: string = '';
  imagePath: string = '';

  constructor(private subscriptionService: SubscriptionService) {}

  ngOnInit(): void {
    this.imagePath = this.imagePath = 'assets/icons/' + this.type + '.png';
  }

  unsubscribe() {
    this.subscriptionService
      .unsubscribe(this.type, this.entity.id)
      .subscribe((data) => {
        window.location.href = '/client-db/SUBSCRIPTIONS';
      });
  }
}
