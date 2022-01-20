import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { server } from '../app-global';
import { ActionModel } from '../model/action-model';
import { Client } from '../model/client-model';
import { SearchFilter } from '../model/search-filter-model';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  getClientsWithReservationInTheMoment(): Observable<Client[]> {
    const id = this.loginService.getCurrentUser().id;
    const url = this.url + '/users-with-reservation/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.get<Client[]>(url, { headers: headers });
  }
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

  clientDatesOverlap(filter: SearchFilter) {
    const id = this.loginService.getCurrentUser().id;
    const url = this.url + '/client-dates-overlap/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.post<any>(url, filter, { headers: headers });
  }

  isActionCanceledByClient(actionId: number) {
    const id = this.loginService.getCurrentUser().id;
    const url = this.url + '/canceled-action/' + id + '/' + actionId;
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }

  getClientsWithCottageReservationAtTheMoment(id: number): Observable<Client[]> {
    const url = this.url + '/users-with-cottage-reservation/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.get<Client[]>(url, { headers: headers });
  }

  getClientsWithBoatReservationAtTheMoment(id: number): Observable<Client[]> {
    const url = this.url + '/users-with-boat-reservation/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.get<Client[]>(url, { headers: headers });
  }
}
