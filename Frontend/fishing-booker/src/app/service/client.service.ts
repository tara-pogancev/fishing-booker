import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { server } from '../app-global';
import { Client } from '../model/client-model';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  url = server + 'api/client';

  constructor(private _http: HttpClient, private loginService: LoginService) {}

  getClient(id: number) {
    const url = this.url + '/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.get<Client>(url, { headers: headers });
  }

  getCurrentClient() {
    const id = this.loginService.getCurrentUser().id;
    const url = this.url + '/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.get<Client>(url, { headers: headers });
  }
}
