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

  constructor(private loginService: LoginService) {
    let currentUser: ActiveUser = JSON.parse(
      localStorage.getItem('currentUser')!
    );
    if (currentUser.role != 'LOGGED_OUT' && currentUser != null) {
      this.user = currentUser;
    }
  }

  ngOnInit(): void {}

  logOut() {
    this.loginService.logout();
  }
}
