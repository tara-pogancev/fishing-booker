import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from 'src/app/app-global';
import { ComplaintModel } from '../model/complaint-model';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root',
})
export class ComplaintService {

  createResponse(comment: any,id:any) {
    const url = this.url + '/create-response';
    const headers = this.loginService.getHeaders();
    return this._http.post<any>(url,{response:comment,complaintId:id},{ headers: headers });
  }
  url = server + 'api/complaints';
  constructor(private _http: HttpClient, private loginService: LoginService) {}

  getClientAvailableComplaints() {
    const id = this.loginService.getCurrentUser().id;
    const url = this.url + '/available-complaints/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }

  newComplaint(complaint: ComplaintModel) {
    const id = this.loginService.getCurrentUser().id;
    complaint.userId = id;
    const url = this.url + '/new-complaint';
    const headers = this.loginService.getHeaders();
    return this._http.post<any>(url, complaint, { headers: headers });
  }

  getWaitingComplaints(){
    const url = this.url + '/get-waiting-complaints';
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }
}
