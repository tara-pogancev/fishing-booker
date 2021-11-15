import { Component, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';
import { UserModel } from 'src/app/model/user-model';
import { ClientService } from 'src/app/service/client.service';
import { UserHeaderComponent } from '../../header/user-header/user-header.component';

@Component({
  selector: 'app-client-dashboard',
  templateUrl: './client-dashboard.component.html',
  styleUrls: ['./client-dashboard.component.css'],
})
export class ClientDashboardComponent implements OnInit {
  client: Client = new Client();
  activeTab: string = 'SPECIAL_OFFERS';

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
