import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from '../app-global';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class CottageOwnerService {

  url=server+'api/cottage-owners';
  constructor(private loginService:LoginService,private _http:HttpClient) { }

  getEnabledOwners(){ //return owners that are enabled by administrators
    const headers=this.loginService.getHeaders();
    return this._http.get<any>(this.url,{headers:headers});
  }

  deleteCottageOwner(id:String){
    const headers=this.loginService.getHeaders();
    return this._http.delete<any>(this.url+'/'+id,{headers:headers});
  }

  getCurrentCottageOwner() {
    const id = this.loginService.getCurrentUser().id;
    const url = this.url + '/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }

  updateCottageOwnerData(cottageOwner: any) {
    const headers = this.loginService.getHeaders();
    return this._http.put<any>(this.url, cottageOwner, { headers: headers });
  }
}