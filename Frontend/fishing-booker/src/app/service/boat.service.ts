import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from '../app-global';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root',
})
export class BoatService {
  url = server + 'browse/boats';

  constructor(private _http: HttpClient,private loginService:LoginService) {}

  findById(id: number) {
    return this._http.get<any>(this.url + '/' + id);
  }

  findAll() {
    return this._http.get<any>(this.url);
  }

  getBoats(){ //admin reading cottages
    const url=server+'api/boats';
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url,{headers:headers});
  }

  deleteBoat(id:any){
    const url=server+'api/boats/'+id;
    const headers = this.loginService.getHeaders();
    return this._http.delete<any>(url,{headers:headers});
  }
}
