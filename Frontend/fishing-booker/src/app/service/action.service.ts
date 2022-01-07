import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from '../app-global';
import { ActionModel } from '../model/action-model';
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

  bookAction(action: ActionModel) {
    const id = this.loginService.getCurrentUser().id;
    const url =
      server +
      'api/client/book-action/' +
      id +
      '/' +
      action.entityType.toLowerCase() +
      '/' +
      action.id;
    const headers = this.loginService.getHeaders();
    return this._http.post<any>(url, action, { headers: headers });
  }
}
