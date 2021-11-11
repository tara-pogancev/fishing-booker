import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
  HttpResponse,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
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

  loginSetUser(activeUser: ActiveUser) {
    this.user = activeUser;
    console.log(this.user);
    localStorage.setItem('currentUser', JSON.stringify(this.user));
    window.location.href = '/';
  }

  logout() {
    this.user = new ActiveUser();
    localStorage.setItem('currentUser', JSON.stringify(this.user));
    window.location.href = '/';
  }

  getCurrentUser(): ActiveUser {
    return JSON.parse(localStorage.getItem('currentUser')!);
  }

  getHeaders() {
    const jwt = this.getCurrentUser().jwt;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ` + jwt,
    });
    return headers;
  }
}
