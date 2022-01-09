import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NumberValueAccessor } from '@angular/forms';
import { server } from '../app-global';
import { AdventureQuickReservation } from '../model/adventure-quick-reservation-model';
import { CreateAdventureModel } from '../model/create-adventure-model';
import { InstructorNewReservation } from '../model/instructor-new-reservation';
import { ReservationModel } from '../model/reservation-model';
import { SearchFilter } from '../model/search-filter-model';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root',
})
export class AdvetnureService {
  getAdventureUtilities(adventureId: number) {
    const id = this.loginService.getCurrentUser().id;
    const url = server + 'api/adventures/get-adventure-utilities/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }

  createAdventureReservation(reservation: InstructorNewReservation) {
    const url = server + 'api/adventures/add-reservation';
    const headers = this.loginService.getHeaders();
    return this._http.post<any>(url, reservation, { headers: headers });
  }
  url = server + 'browse/adventures';

  constructor(private _http: HttpClient, private loginService: LoginService) {}

  findById(id: number) {
    return this._http.get<any>(this.url + '/' + id);
  }

  findAll() {
    return this._http.get<any>(this.url);
  }

  createAdventure(adventure: CreateAdventureModel) {
    const url = server + 'api/adventures/add-adventure';
    const headers = this.loginService.getHeaders();
    adventure.ownerId = this.loginService.getCurrentUser().id;
    return this._http.post<any>(url, adventure, { headers: headers });
  }

  getInstructorAdventures() {
    const url = server + 'api/adventures/get-instructor-adventures';
    const headers = this.loginService.getHeaders();
    const instructorId = this.loginService.getCurrentUser().id;
    return this._http.get<any>(url + '/' + instructorId, { headers: headers });
  }

  getSearch(filter: SearchFilter) {
    const headers = this.loginService.getHeaders();
    const userId = this.loginService.getCurrentUser().id;
    const url = server + 'api/adventures/search/' + userId;
    return this._http.post<any>(url, filter, { headers: headers });
  }

  getAdventure(id: any) {
    const url = server + 'api/adventures/';
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url + id, { headers: headers });
  }

  deleteAdventure(id: number) {
    const localurl = server + 'api/adventures/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.delete<any>(localurl, { headers: headers });
  }

  bookAdventure(reservation: ReservationModel) {
    const url = server + 'api/adventures/book';
    const headers = this.loginService.getHeaders();
    return this._http.post<any>(url, reservation, { headers: headers });
  }

  getReviews(id: number) {
    const url = this.url + '/reviews/' + id;
    return this._http.get<any>(url);
  }
  createAdventureQuickReservation(reservation: AdventureQuickReservation) {
    const url = server + 'api/adventures/add-quick-reservation';
    const headers = this.loginService.getHeaders();
    return this._http.post<any>(url, reservation, { headers: headers });
  }

  getQuickReservationForCalendar(id: number) {
    const url = server + 'api/adventures/get-quick-reservations-calendar/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }

  getReservation(id: number) {
    const url = server + 'api/adventures/get-reservation/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }

  getQuickReservation(id: number) {
    const url = server + 'api/adventures/get-quick-reservation/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }
}
