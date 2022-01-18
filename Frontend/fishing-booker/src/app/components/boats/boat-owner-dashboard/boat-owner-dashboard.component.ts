import { Component, OnInit } from '@angular/core';
import { BoatOwnerService } from 'src/app/service/boat-owner.service';

@Component({
  selector: 'app-boat-owner-dashboard',
  templateUrl: './boat-owner-dashboard.component.html',
  styleUrls: ['./boat-owner-dashboard.component.css']
})
export class BoatOwnerDashboardComponent implements OnInit {
  boatOwner: any;
  activeTab: string = 'PERSONAL_INFO';

  constructor(private boatOwnerService: BoatOwnerService) { }

  ngOnInit(): void {
    this.boatOwnerService.getCurrentBoatOwner().subscribe(
      (data: any) => {
        this.boatOwner = data;
        console.log(data);
      }
    );
  }

  changeTab(tabName: string) {
    this.activeTab = tabName;
  }

}
