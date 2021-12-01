import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from '../app-global';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  

  url = server + 'api/admin';


  constructor(private _http: HttpClient, private loginService: LoginService) {}

  getClient(id: number) {
    const url = this.url + '/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }

  getCurrentAdmin() {
    const id = this.loginService.getCurrentUser().id;
    const url = this.url + '/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }

  updateClientData(client: any) {
    const headers = this.loginService.getHeaders();
    return this._http.put<any>(this.url, client, { headers: headers });
  }

  loadClients(){
    return this._http.get<any>(this.url);
  }

  createAdmin(administrator:any){
    const headers=this.loginService.getHeaders();
    return this._http.post<any>(this.url,administrator,{headers:headers});
  }

  changePassword(id: any, password: string) {
    const headers=this.loginService.getHeaders();
    this._http.put<any>(this.url+'/change-password',{id:id,password:password},{headers:headers}).subscribe();
  }
}
