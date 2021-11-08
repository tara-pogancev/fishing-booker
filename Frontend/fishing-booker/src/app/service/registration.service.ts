import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from 'src/app/app-global';
import { UserModel } from 'src/app/model/user-model';

@Injectable({
  providedIn: 'root',
})
export class RegistrationService {
  url = server + 'register';
  constructor(private _http: HttpClient) {}

  register(user: UserModel) {
    return this._http.post<any>(this.url, user);
  }

  validateEmail(email: String) {
    let emailUrl = this.url + '/isEmailUnique/' + email;
    return this._http.get<boolean>(emailUrl);
  }
}
