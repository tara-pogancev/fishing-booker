import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from '../app-global';
import { AvailableInstructorTimePeriod } from '../model/available-instructor-time-period';
import { AvailableTimePeriod } from '../model/available-time-period';
import { ChangeTimeSlot } from '../model/change-time-slot';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class FishingInstructorService {


  updateFreeSlot(timePeriod: ChangeTimeSlot) {
    const headers = this.loginService.getHeaders();
    const id = this.loginService.getCurrentUser().id;
    timePeriod.instructorId=id;
    const url =server+'api/available-instructor-time';
    return this._http.put<any>(url,timePeriod,{headers:headers});
  }


  getInstructorReservations() {
    const id = this.loginService.getCurrentUser().id;
    const url = this.url + '/get-instructor-reservations/'+id;
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url,{headers:headers});
  }

  getInstructorPastReservations() {
    const id = this.loginService.getCurrentUser().id;
    const url = this.url + '/get-instructor-past-reservations/'+id;
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url,{headers:headers});
  }



  deleteAvailableTimePeriod(id:number){
    const headers = this.loginService.getHeaders();
    const url =server+'api/available-instructor-time/'+id;
    return this._http.delete<any>(url,{headers:headers});
  }
  

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

  addAvailableTime(time:AvailableInstructorTimePeriod){
    const headers = this.loginService.getHeaders();
    const id = this.loginService.getCurrentUser().id;
    time.instructorId=id;
    const url =server+'api/available-instructor-time';
    return this._http.post<any>(url,time,{headers:headers});
  }

  getAvailableTimes() {
    const headers = this.loginService.getHeaders();
    const id = this.loginService.getCurrentUser().id;
    const url =server+'api/available-instructor-time/'+id;
    return this._http.get<any>(url,{headers:headers});

  }
}
