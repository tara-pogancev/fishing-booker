import { Component, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';
import { ClientService } from 'src/app/service/client.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {

  

  client: Client = new Client();
  activeTab: string = 'PERSONAL_INFO';

  constructor(private clientService: ClientService) {}

  ngOnInit(): void {
    this.clientService.getCurrentClient().subscribe((data: Client) => {
      this.client = data;
      console.log(data);
    });
  }

  changeTab(tabName: string) {
    this.activeTab = tabName;
  }

}
