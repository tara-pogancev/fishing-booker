import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { server } from '../app-global';
import { DeleteAccountRequest } from '../model/delete-request-model';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root',
})
export class DeleteAccountService {
  constructor(private _http: HttpClient, private loginService: LoginService) {}

  getRequests(): Observable<DeleteAccountRequest[]> {
    const url = server + 'api/delete-account';
    const headers = this.loginService.getHeaders();
    return this._http.get<DeleteAccountRequest[]>(url, { headers: headers });
  }

  approveRequest(id: string, description: string){
    const headers = this.loginService.getHeaders();
    const url = server + 'api/delete-account/approve';
    return this._http.put<any>(url, { id: id, description: description }, { headers: headers });
  }

  rejectRequest(id: string, description: string) {
    const headers = this.loginService.getHeaders();
    const url = server + 'api/delete-account/reject';
    return this._http
      .put<any>(url, { id: id, description: description }, { headers: headers })
      
  }

  sendRequest(description: string): void {
    const userId = this.loginService.getCurrentUser().id;
    const headers = this.loginService.getHeaders();
    const url = server + 'api/delete-account';
    this._http
      .post<any>(
        url,
        { userId: userId, description: description },
        { headers: headers }
      )
      .subscribe((data) => {
        if (data == false) {
          alert('Your request is not sent!');
        } else {
          alert('Your request is successfully sent!');
        }
      });
  }

  checkForActiveRequests() {
    const userId = this.loginService.getCurrentUser().id;
    const headers = this.loginService.getHeaders();
    const url = server + 'api/delete-account/active-request/' + userId;
    return this._http.get<any>(url, { headers: headers });
  }
}
