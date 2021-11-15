import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { config } from 'src/config';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root',
})
export class MapService {
  url_begin = 'https://maps.googleapis.com/maps/api/geocode/json?address=';
  url_end = '&key=' + config.API_TOKEN;

  constructor(private _http: HttpClient) {}

  getCoordinates(address: string) {
    address = address.split(' ').join('+');
    address = address.replace(',', '');
    return this._http.get<any>(this.url_begin + address + this.url_end);
  }
}
