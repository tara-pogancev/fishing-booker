import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class FishingInstructorService {

  url = server + 'api/fishing-instructor';
  constructor(private _http: HttpClient) { }

  loadInstructors():any{
      return this._http.get<any>(this.url);
  }
}
