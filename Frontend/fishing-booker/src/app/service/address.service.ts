import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from '../app-global';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root',
})
export class AddressService {
  url = server + 'api/address';

  constructor(private _http: HttpClient, private loginService: LoginService) {}

  getCountries() {
    const url = this.url + '/countries';
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }
}
