import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from '../app-global';
import { AuthRequest } from '../model/user-model';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  url = server + 'login';
  constructor(private _http: HttpClient) {}

  register(user: AuthRequest) {
    return this._http.post<any>(this.url, AuthRequest);
  }



}
