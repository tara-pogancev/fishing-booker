import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { CreateAdventureReservationReport } from "../model/create-advenutre-reservation-report";
import { server } from 'src/app/app-global';
import { RegistrationService } from "./registration.service";
import { LoginService } from "./login.service";
import { AdventureReservationReport } from "../model/adventure-reservation-report";
@Injectable({
    providedIn: 'root',
  })
export class ReservationReportService {
    forgiveClient(report:AdventureReservationReport) {

        const url=server+'api/adventure-reservation-report/forgive-client';
        const headers=this.loginService.getHeaders();
        return this._http.put<any>(url,report,{headers : headers});
    }
    givePenalty(report:AdventureReservationReport) {
        const url=server+'api/adventure-reservation-report/give-penalty';
        const headers=this.loginService.getHeaders();
        return this._http.put<any>(url,report,{headers : headers});
    }
    constructor(private _http: HttpClient,private loginService:LoginService) {}
  
    createAdventureReservationReport(report:CreateAdventureReservationReport){
        const url=server+'api/adventure-reservation-report'+'/add-adventure-reservation-report';
        const headers=this.loginService.getHeaders();
        return this._http.post<any>(url,report,{headers : headers});
    }

    createCottageReservationReport(report:CreateAdventureReservationReport){
        const url=server+'api/cottage-reservation-report'+'/add-cottage-reservation-report';
        const headers=this.loginService.getHeaders();
        return this._http.post<any>(url,report,{headers : headers});
    }

    createBoatReservationReport(report:CreateAdventureReservationReport){
        const url=server+'api/boat-reservation-report'+'/add-boat-reservation-report';
        const headers=this.loginService.getHeaders();
        return this._http.post<any>(url,report,{headers : headers});
    }

    getReservationReportsForAdmin(){
        const url=server+'api/adventure-reservation-report/unprocessed';
        const headers=this.loginService.getHeaders();
        return this._http.get<any>(url,{headers : headers});
    }
  }