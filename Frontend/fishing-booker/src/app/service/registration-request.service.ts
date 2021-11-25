import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { server } from '../app-global';
import { RegistrationRequest } from '../model/registration-request-model';
import { LoginService } from './login.service';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RegistrationRequestService {

  url = server + 'api/registration-requests';
  constructor(private _http: HttpClient,private loginService: LoginService) { }

  getRequests() : Observable<RegistrationRequest[]>{
    const headers = this.loginService.getHeaders();
    return this._http.get<RegistrationRequest[]>(this.url,{headers:headers});
  }

  approveRequest(id:string):void{
    const headers = this.loginService.getHeaders();
    this._http.put<any>(this.url+'/approve/'+id,{},{headers:headers}).subscribe(console.log);
  }

  rejectRequest(id:string,explanation:string){
    const headers = this.loginService.getHeaders();
    this._http.put<any>(this.url+'/reject',{id:id,causeOfRejection:explanation},{headers:headers}).subscribe(console.log);
  }

  erroHandler(error: HttpErrorResponse) {
    return throwError(error.message || 'server Error');
  }
  
}
