import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from '../app-global';
import { AdventureQuickReservation } from '../model/adventure-quick-reservation-model';
import { CreateCottageModel } from '../model/create-cottage-model';
import { InstructorNewReservation } from '../model/instructor-new-reservation';
import { NewReservation } from '../model/new-reservation';
import { ReservationModel } from '../model/reservation-model';
import { SearchFilter } from '../model/search-filter-model';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root',
})
export class CottageService {
  url = server + 'browse/cottages';

  constructor(private _http: HttpClient, private loginService: LoginService) {}

  findById(id: number) {
    return this._http.get<any>(this.url + '/' + id);
  }

  findAll() {
    return this._http.get<any>(this.url);
  }

  getCottages() {
    //admin reading cottages
    const url = server + 'api/cottages';
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }

  getSearch(filter: SearchFilter) {
    const headers = this.loginService.getHeaders();
    const userId = this.loginService.getCurrentUser().id;
    const url = server + 'api/cottages/search/' + userId;
    return this._http.post<any>(url, filter, { headers: headers });
  }

  bookCottage(reservation: ReservationModel) {
    const url = server + 'api/cottages/book';
    const headers = this.loginService.getHeaders();
    return this._http.post<any>(url, reservation, { headers: headers });
  }

  getCottageReservations(id: number) {
    const url = this.url + '/reservations/' + id;
    return this._http.get<any>(url);
  }

  getCottage(id: any) {
    const url = server + 'api/cottages/';
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url + id, { headers: headers });
  }

  deleteCottage(id: any) {
    const url = server + 'api/cottages/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.delete<any>(url, { headers: headers });
  }

  findByCottageOwnerId(id: any) {
    const url = server + 'api/cottages/findByCottageOwnerId/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }

  createCottage(cottage: CreateCottageModel) {
    const url = server + 'api/cottages/add-cottage';
    const headers = this.loginService.getHeaders();
    cottage.ownerId = this.loginService.getCurrentUser().id;
    return this._http.post<any>(url, cottage, { headers: headers });
  }

  getReviews(id: number) {
    const url = this.url + '/reviews/' + id;
    return this._http.get<any>(url);
  }

  createCottageQuickReservation(reservation: AdventureQuickReservation) {
    const url = server + 'api/cottages/add-quick-reservation';
    const headers = this.loginService.getHeaders();
    return this._http.post<any>(url, reservation, { headers: headers });
  }

  createCottageReservation(reservation: NewReservation) {
    const url = server + 'api/cottages/add-reservation';
    const headers = this.loginService.getHeaders();
    return this._http.post<any>(url, reservation, { headers: headers });
  }

  getCottageUtilities(cottageId: number) {
    const id = this.loginService.getCurrentUser().id;
    const url = server + 'api/cottages/get-cottage-utilities/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }
}
