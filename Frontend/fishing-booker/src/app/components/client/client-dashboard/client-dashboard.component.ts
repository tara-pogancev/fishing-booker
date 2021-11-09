import { Component, OnInit } from '@angular/core';
import { UserModel } from 'src/app/model/user-model';
import { UserHeaderComponent } from '../../header/user-header/user-header.component';

@Component({
  selector: 'app-client-dashboard',
  templateUrl: './client-dashboard.component.html',
  styleUrls: ['./client-dashboard.component.css'],
})
export class ClientDashboardComponent implements OnInit {
  user: UserModel = new UserModel();
  activeTab: string = 'PERSONAL_INFO';

  constructor() {}

  ngOnInit(): void {}
}
