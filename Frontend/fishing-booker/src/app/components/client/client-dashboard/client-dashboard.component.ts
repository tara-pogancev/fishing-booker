import { Component, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';
import { UserModel } from 'src/app/model/user-model';
import { ClientService } from 'src/app/service/client.service';
import { LoginService } from 'src/app/service/login.service';
import { UserHeaderComponent } from '../../header/user-header/user-header.component';

@Component({
  selector: 'app-client-dashboard',
  templateUrl: './client-dashboard.component.html',
  styleUrls: ['./client-dashboard.component.css'],
})
export class ClientDashboardComponent implements OnInit {
  client: Client = new Client();
  activeTab: string = 'PERSONAL_INFO';

  constructor(
    private clientService: ClientService,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    this.clientService.getCurrentClient().subscribe(
      (data: Client) => {
        this.client = data;
      },
      (err) => {
        if (err.status == 401) {
          window.location.href = '/401';
        }
        if (err.status == 404) {
          this.loginService.logout();
          window.location.href = '/login';
        }
      }
    );
  }

  changeTab(tabName: string) {
    this.activeTab = tabName;
  }
}
