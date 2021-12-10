import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from '../app-global';
import { AvailableCottageTimePeriod } from '../model/available-cottage-time-period';
import { LoginService } from './login.service';

@Injectable({
    providedIn: 'root',
})
export class AvailableCottageTimePeriodService {
    url = server + 'api/availableCottageTimePeriods';

    constructor(private _http: HttpClient, private loginService: LoginService) { }

    findAll() {
        const headers = this.loginService.getHeaders();
        return this._http.get<any>(this.url,{headers:headers});
    }

    addAvailableCottageTimePeriod(availableCottageTimePeriod: AvailableCottageTimePeriod) {
        const headers=this.loginService.getHeaders();
        return this._http.post<any>(this.url,availableCottageTimePeriod,{headers:headers});
    }
}