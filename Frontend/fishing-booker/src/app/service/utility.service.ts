import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from '../app-global';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class UtilityService {

  url = server + 'api/utilities';
  constructor(private _http: HttpClient,private loginService: LoginService) { }


  getUtilities(){
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(this.url,{headers:headers});
  }
}
