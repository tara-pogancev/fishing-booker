import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from '../app-global';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class BoatOwnerService {

  url=server+'api/boat-owners';
  constructor(private loginService:LoginService,private _http:HttpClient) { }

  getEnabledOwners(){ //return owners that are enabled by administrators
    const headers=this.loginService.getHeaders();
    return this._http.get<any>(this.url,{headers:headers});
  }

  deleteBoatOwner(id:String){
    const headers=this.loginService.getHeaders();
    return this._http.delete<any>(this.url+'/'+id,{headers:headers});
  }

  getCurrentBoatOwner() {
    const id = this.loginService.getCurrentUser().id;
    const url = this.url + '/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }

  updateBoatOwnerData(boatOwner: any) {
    const headers = this.loginService.getHeaders();
    return this._http.put<any>(this.url, boatOwner, { headers: headers });
  }

  getPastReservations(id:number){
    const url = this.url + '/' + id + '/past-reservations';
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }
}
