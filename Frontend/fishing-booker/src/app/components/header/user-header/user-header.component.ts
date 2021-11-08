import { Component, OnInit } from '@angular/core';
import { ActiveUser } from 'src/app/model/user-model';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'user-header',
  templateUrl: './user-header.component.html',
  styleUrls: ['./user-header.component.css'],
})
export class UserHeaderComponent implements OnInit {
  user = new ActiveUser();
  db_url = '/';

  constructor(private loginService: LoginService) {}

  ngOnInit(): void {
    let currentUser: ActiveUser = JSON.parse(
      localStorage.getItem('currentUser')!
    );
    if (currentUser.role != 'LOGGED_OUT' && currentUser != null) {
      this.user = currentUser;
      switch (this.user.role) {
        case 'REGISTERED_CLIENT':
          this.db_url = '/client-db';
          break;
        case 'COTTAGE_OWNER':
          this.db_url = '/cottage-owner-db';
          break;
        case 'BOAT_OWNER':
          this.db_url = '/boat-owner-db';
          break;
        case 'FISHING_INSTRUCTOR':
          this.db_url = '/instructor-db';
          break;
        case 'ADMINISTRATOR':
          this.db_url = '/admin-db';
          break;
      }
    }
  }

  logOut() {
    this.loginService.logout();
  }
}
