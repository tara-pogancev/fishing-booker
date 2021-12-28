import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from '../app-global';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root',
})
export class SubscriptionService {
  url = server + 'api/subscriptions';
  constructor(private _http: HttpClient, private loginService: LoginService) {}

  subscribe(type: string, entityId: number) {
    const id = this.loginService.getCurrentUser().id;
    const url = this.url + '/subscribe/' + id + '/' + type + '/' + entityId;
    const headers = this.loginService.getHeaders();
    return this._http.put<any>(url, {}, { headers: headers });
  }

  unsubscribe(type: string, entityId: number) {
    const id = this.loginService.getCurrentUser().id;
    const url = this.url + '/unsubscribe/' + id + '/' + type + '/' + entityId;
    const headers = this.loginService.getHeaders();
    return this._http.put<any>(url, {}, { headers: headers });
  }

  getBoatSubscriptions() {
    const id = this.loginService.getCurrentUser().id;
    const url = this.url + '/boats/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }
}
