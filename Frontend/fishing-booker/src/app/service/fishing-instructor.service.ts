import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from '../app-global';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class FishingInstructorService {

  url = server + 'api/fishing-instructor';
  constructor(private _http: HttpClient,private loginService: LoginService) { }

  loadInstructors():any{
      const headers = this.loginService.getHeaders();
      return this._http.get<any>(this.url,{headers:headers});
  }
}
