import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from '../app-global';
import { CreateCottageModel } from '../model/create-cottage-model';
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
    const url = server + 'api/cottages/search';
    const headers = this.loginService.getHeaders();
    return this._http.post<any>(url, filter, { headers: headers });
  }

  bookCottage(reservation: ReservationModel) {
    const url = server + 'api/cottages/book';
    const headers = this.loginService.getHeaders();
    return this._http.post<any>(url, reservation, { headers: headers });
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
}
