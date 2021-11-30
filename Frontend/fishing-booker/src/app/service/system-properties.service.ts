import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from '../app-global';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class SystemPropertiesService {

  url=server+'api/system-properties';
  constructor(private _http: HttpClient, private loginService: LoginService) { 

  }

  getPercentage(){
    const headers=this.loginService.getHeaders();
    return this._http.get<any>(this.url+'/get-commission',{headers:headers})
  }

  setPercentage(value:Number){
    const headers=this.loginService.getHeaders();
    return this._http.put<any>(this.url+'/set-percentage/'+value,{},{headers:headers});
  }
}
