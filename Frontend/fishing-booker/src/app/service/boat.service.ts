import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from '../app-global';
import { SearchFilter } from '../model/search-filter-model';
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

  getSearch(filter : SearchFilter) {
    const url=server+'api/boats/search';
    const headers = this.loginService.getHeaders();
    return this._http.post<any>(url,filter,{headers:headers});
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
