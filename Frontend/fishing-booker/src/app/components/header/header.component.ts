import { Component, OnInit } from '@angular/core';
import { ActiveUser } from 'src/app/model/user-model';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  user = new ActiveUser();
  isActive: boolean = false;

  constructor(private loginService: LoginService) {}

  ngOnInit(): void {
    this.refreshUser();
  }

  refreshUser(): void {
    let currentUser: ActiveUser = JSON.parse(
      localStorage.getItem('currentUser')!
    );
    if (currentUser.role != 'LOGGED_OUT' && currentUser != null) {
      this.user = currentUser;
    }

    this.isActive = this.user.role != 'LOGGED_OUT';
  }
}
