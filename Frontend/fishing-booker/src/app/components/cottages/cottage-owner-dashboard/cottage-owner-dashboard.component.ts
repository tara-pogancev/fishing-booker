import { Component, OnInit } from '@angular/core';
import { CottageOwnerService } from 'src/app/service/cottage-owner.service';

@Component({
  selector: 'app-cottage-owner-dashboard',
  templateUrl: './cottage-owner-dashboard.component.html',
  styleUrls: ['./cottage-owner-dashboard.component.css']
})
export class CottageOwnerDashboardComponent implements OnInit {
  cottageOwner: any;
  activeTab: string = 'PERSONAL_INFO';

  constructor(
    private cottageOwnerService: CottageOwnerService
  ) { }

  ngOnInit(): void {
    this.cottageOwnerService.getCurrentCottageOwner().subscribe(
      (data: any) => {
        this.cottageOwner = data;
        console.log(data);
      }
    );
  }

  changeTab(tabName: string) {
    this.activeTab = tabName;
  }

}
