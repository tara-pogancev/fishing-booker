import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root',
})
export class ImageService {
  url = server + 'browse/image';

  constructor(private _http: HttpClient) {}

  getImage(id: number): Observable<Blob> {
    const url = this.url + '/' + id;
    return this._http.get(url, { responseType: 'blob' });
  }

}
