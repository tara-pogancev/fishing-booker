import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { server } from '../app-global';
import { RegistrationRequest } from '../model/registration-request-model';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class RegistrationRequestService {

  url = server + 'api/registration-requests';
  constructor(private _http: HttpClient,private loginService: LoginService) { }

  getRequests() : Observable<RegistrationRequest[]>{
    const headers = this.loginService.getHeaders();
    return this._http.get<RegistrationRequest[]>(this.url);
  }
  
}
