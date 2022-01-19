import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from '../app-global';
import { AvailableBoatTimePeriod } from '../model/available-boat-time-period';
import { LoginService } from './login.service';

@Injectable({
    providedIn: 'root',
})
export class AvailableBoatTimePeriodService {
    url = server + 'api/availableBoatTimePeriods';

    constructor(private _http: HttpClient, private loginService: LoginService) { }

    findAll() {
        const headers = this.loginService.getHeaders();
        return this._http.get<any>(this.url,{headers:headers});
    }

    addAvailableBoatTimePeriod(availableBoatTimePeriod: AvailableBoatTimePeriod) {
        const headers=this.loginService.getHeaders();
        return this._http.post<any>(this.url,availableBoatTimePeriod,{headers:headers});
    }
}