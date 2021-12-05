import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from '../app-global';
import { CreateAdventureModel } from '../model/create-adventure-model';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root',
})
export class AdvetnureService {
  url = server + 'browse/adventures';

  constructor(private _http: HttpClient,private loginService:LoginService) {}

  findById(id: number) {
    return this._http.get<any>(this.url + '/' + id);
  }

  findAll() {
    return this._http.get<any>(this.url);
  }

  createAdventure(adventure:CreateAdventureModel){
    const url=server+'api/adventures/add-adventure'
    const headers=this.loginService.getHeaders();
    adventure.ownerId=this.loginService.getCurrentUser().id;
    return this._http.post<any>(url,adventure,{headers:headers});
  }
}
