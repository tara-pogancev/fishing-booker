import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from '../app-global';
import { AdventureQuickReservation } from '../model/adventure-quick-reservation-model';
import { CreateBoatModel } from '../model/create-boat-model';
import { NewReservation } from '../model/new-reservation';
import { ReservationModel } from '../model/reservation-model';
import { SearchFilter } from '../model/search-filter-model';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root',
})
export class BoatService {
  url = server + 'browse/boats';

  constructor(private _http: HttpClient, private loginService: LoginService) {}

  findById(id: number) {
    return this._http.get<any>(this.url + '/' + id);
  }

  getBoatReservations(id: number) {
    const url = this.url + '/reservations/' + id;
    return this._http.get<any>(url);
  }

  findAll() {
    return this._http.get<any>(this.url);
  }

  getSearch(filter: SearchFilter) {
    const headers = this.loginService.getHeaders();
    const userId = this.loginService.getCurrentUser().id;
    const url = server + 'api/boats/search/' + userId;
    return this._http.post<any>(url, filter, { headers: headers });
  }

  getBoats() {
    //admin reading boats
    const url = server + 'api/boats';
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }

  deleteBoat(id: any) {
    const url = server + 'api/boats/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.delete<any>(url, { headers: headers });
  }

  deleteBoatByAdmin(id: any) {
    const url = server + 'api/boats/admin-delete/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.delete<any>(url, { headers: headers });
  }



  bookBoat(reservation: ReservationModel) {
    const url = server + 'api/boats/book';
    const headers = this.loginService.getHeaders();
    return this._http.post<any>(url, reservation, { headers: headers });
  }

  getReviews(id: number) {
    const url = this.url + '/reviews/' + id;
    return this._http.get<any>(url);
  }

  findByBoatOwnerId(id: any) {
    const url = server + 'api/boats/findByBoatOwnerId/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }

  createBoat(boat: CreateBoatModel) {
    const url = server + 'api/boats/add-boat';
    const headers = this.loginService.getHeaders();
    boat.ownerId = this.loginService.getCurrentUser().id;
    return this._http.post<any>(url, boat, { headers: headers });
  }

  getBoatTypes() {
    const url = server + 'api/boats/getBoatTypes/';
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }

  getNavigationalEquipment() {
    const url = server + 'api/boats/getNavigationalEquipment/';
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }

  getBoat(id: any) {
    const url = server + 'api/boats/';
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url + id, { headers: headers });
  }

  createBoatQuickReservation(reservation: AdventureQuickReservation) {
    const url = server + 'api/boats/add-quick-reservation';
    const headers = this.loginService.getHeaders();
    return this._http.post<any>(url, reservation, { headers: headers });
  }

  createBoatReservation(reservation: NewReservation) {
    const url = server + 'api/boats/add-reservation';
    const headers = this.loginService.getHeaders();
    return this._http.post<any>(url, reservation, { headers: headers });
  }

  getBoatUtilities(boatId: number) {
    const id = this.loginService.getCurrentUser().id;
    const url = server + 'api/boats/get-boat-utilities/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }
}
