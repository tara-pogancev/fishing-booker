import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthRequest } from 'src/app/model/user-model';
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
}
