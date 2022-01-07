import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from '../app-global';
import { ReservationModel } from '../model/reservation-model';
import { SearchFilter } from '../model/search-filter-model';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root',
})
export class ActionService {
  url = server + 'browse/actions';

  constructor(private _http: HttpClient, private loginService: LoginService) {}

  findAll() {
    return this._http.get<any>(this.url);
  }

  /*  bookBoat(reservation: ReservationModel) {
    const url = server + 'api/boats/book';
    const headers = this.loginService.getHeaders();
    return this._http.post<any>(url, reservation, { headers: headers });
  }
*/
}
