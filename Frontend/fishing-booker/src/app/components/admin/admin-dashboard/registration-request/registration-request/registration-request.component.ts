import { Component, OnInit } from '@angular/core';
import { RegistrationRequest } from 'src/app/model/registration-request-model';
import { RegistrationRequestService } from 'src/app/service/registration-request.service';

@Component({
  selector: 'app-registration-request',
  templateUrl: './registration-request.component.html',
  styleUrls: ['./registration-request.component.css']
})
export class RegistrationRequestComponent implements OnInit {

  registrationRequests:RegistrationRequest[]=[];
  constructor(private requestService:RegistrationRequestService) { }

  ngOnInit(): void {
    this.requestService.getRequests().subscribe(requests=>this.registrationRequests=requests) 
  }

}
