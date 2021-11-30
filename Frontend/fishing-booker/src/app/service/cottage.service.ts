import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from '../app-global';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root',
})
export class CottageService {
  url = server + 'browse/cottages';

  constructor(private _http: HttpClient,private loginService:LoginService) {}

  findById(id: number) {
    return this._http.get<any>(this.url + '/' + id);
  }

  findAll() {
    return this._http.get<any>(this.url);
  }

  getCottages(){ //admin reading cottages
    const url=server+'api/cottages';
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url,{headers:headers});
  }

  deleteCottage(id:any){
    const url=server+'api/cottages/'+id;
    const headers = this.loginService.getHeaders();
    return this._http.delete<any>(url,{headers:headers});
  }
}
