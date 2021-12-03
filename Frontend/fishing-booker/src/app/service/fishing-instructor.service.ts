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

  deleteInstructor(id:Number){
    const headers = this.loginService.getHeaders();
    return this._http.delete<any>(this.url+'/'+id,{headers:headers});
  }

  getCurrentInstructor() { //todo controller backend
    const id = this.loginService.getCurrentUser().id;
    const url = this.url + '/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }

  updateInstructor(instructor: any) {
    const headers = this.loginService.getHeaders();
    return this._http.put<any>(this.url, instructor, { headers: headers });
  }
}
