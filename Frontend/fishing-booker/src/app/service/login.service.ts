import {
  HttpClient,
  HttpErrorResponse,
  HttpResponse,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { server } from '../app-global';
import { ActiveUser, AuthRequest } from '../model/user-model';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  url = server + 'login';
  private user = new ActiveUser();

  constructor(private _http: HttpClient, private route: Router) {}

  login(request: AuthRequest) {
    return this._http.post<any>(this.url, request);
  }

  loginSetUser(user: ActiveUser) {
    this.user = user;
    console.log(this.user);
    localStorage.setItem('currentUser', JSON.stringify(user));
  }

  logout() {
    this.user = new ActiveUser();
    localStorage.setItem('currentUser', JSON.stringify(this.user));
    this.route.navigate(['']);
  }
}
