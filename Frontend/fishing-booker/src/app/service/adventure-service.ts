import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from '../app-global';
import { CreateAdventureModel } from '../model/create-adventure-model';
import { ReservationModel } from '../model/reservation-model';
import { SearchFilter } from '../model/search-filter-model';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root',
})
export class AdvetnureService {
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
    const url = server + 'browse/adventures/search';
    return this._http.post<any>(url, filter);
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
}
