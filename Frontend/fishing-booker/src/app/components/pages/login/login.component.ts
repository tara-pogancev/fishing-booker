import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActiveUser, AuthRequest } from 'src/app/model/user-model';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  request = new AuthRequest();
  errorMessage: string = '';

  constructor(private loginService: LoginService, private route: Router) {}

  ngOnInit(): void {}

  login() {
    if (this.request.email == '' || this.request.password == '') {
      this.errorMessage = 'Email or password missing.';
    } else {
      this.loginService.login(this.request).subscribe(
        (data) => this.successfulLogin(data),
        (res) => (this.errorMessage = 'Invalid email or password.')
      );
    }
  }

  successfulLogin(data: ActiveUser) {
    this.errorMessage = '';
    console.log(data);
    this.loginService.loginSetUser(data);
  }
}
