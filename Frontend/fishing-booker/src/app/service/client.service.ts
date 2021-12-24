import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { server } from '../app-global';
import { Client } from '../model/client-model';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  url = server + 'api/client';

  constructor(private _http: HttpClient, private loginService: LoginService) {}

  getClient(id: number) {
    const url = this.url + '/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.get<Client>(url, { headers: headers });
  }

  getCurrentClient() {
    const id = this.loginService.getCurrentUser().id;
    const url = this.url + '/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.get<Client>(url, { headers: headers });
  }

  updateClientData(client: Client) {
    const headers = this.loginService.getHeaders();
    return this._http.put<any>(this.url, client, { headers: headers });
  }

  loadEnabledClients() {
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(this.url + '/get-enabled', { headers: headers });
  }

  deleteClient(id: string) {
    const headers = this.loginService.getHeaders();
    return this._http.delete<any>(this.url + '/delete-client/' + id, {
      headers: headers,
    }); //.pipe(catchError(this.erroHandler));
  }

  erroHandler(error: HttpErrorResponse) {
    alert('Error');
    return throwError(error.message || 'server Error');
  }

  getClientPastCottageReservations() {
    const id = this.loginService.getCurrentUser().id;
    const url = this.url + '/' + id + '/past-reservations/cottages';
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }

  getClientPastBoatReservations() {
    const id = this.loginService.getCurrentUser().id;
    const url = this.url + '/' + id + '/past-reservations/boats';
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }

  getClientPastAdventureReservations() {
    const id = this.loginService.getCurrentUser().id;
    const url = this.url + '/' + id + '/past-reservations/adventures';
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }

  getClientUpcomingReservations() {
    const id = this.loginService.getCurrentUser().id;
    const url = this.url + '/' + id + '/upcoming-reservations';
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }

  cancelReservation(reservationId: number) {
    const id = this.loginService.getCurrentUser().id;
    const url = this.url + '/' + id + '/cancel/' + reservationId;
    const headers = this.loginService.getHeaders();
    return this._http.delete<any>(url, { headers: headers });
  }
}
