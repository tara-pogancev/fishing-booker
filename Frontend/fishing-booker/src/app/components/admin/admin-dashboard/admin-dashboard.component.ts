import { Component, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';
import { AdminService } from 'src/app/service/admin.service';
import { ClientService } from 'src/app/service/client.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {

  

  admin: any = {};
  activeTab: string = 'PERSONAL_INFO';

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
   this.adminService.getCurrentClient().subscribe((data: Client) => {
      this.admin = data;
      console.log(data);
    });
  }

  changeTab(tabName: string) {
    this.activeTab = tabName;
  }

  

}
