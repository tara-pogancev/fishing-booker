import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root',
})
export class AdvetnureService {
  url = server + 'browse/adventures';

  constructor(private _http: HttpClient) {}

  findById(id: number) {
    return this._http.get<any>(this.url + '/' + id);
  }

  findAll() {
    return this._http.get<any>(this.url);
  }
}
