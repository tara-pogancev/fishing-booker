import { Component, OnInit } from '@angular/core';
import { RegistrationRequest } from 'src/app/model/registration-request-model';
import { RegistrationRequestService } from 'src/app/service/registration-request.service';
import { Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-registration-request',
  templateUrl: './registration-request.component.html',
  styleUrls: ['./registration-request.component.css']
})
export class RegistrationRequestComponent implements OnInit {

  registrationRequests:RegistrationRequest[]=[];
  selectedRequest: RegistrationRequest=new RegistrationRequest();
  comment:string='';
  
  constructor(private requestService:RegistrationRequestService) { }
  activeTab:string='';
  ngOnInit(): void {
    this.requestService.getRequests().subscribe(requests=>this.registrationRequests=requests) 
  }

  acceptRegistration(request:RegistrationRequest):void{
    this.requestService.approveRequest(request.id).subscribe(data=>{
      if (data==false){
        alert('Failure');
      }else{
        this.removeRequestFromFront();
        alert('Successfully approved');
        
      }
    });
    
  }

  removeRequestFromFront(){
    const index=this.registrationRequests.indexOf(this.selectedRequest);
    this.registrationRequests.splice(index,1);
  }

  openModalTab(request:RegistrationRequest):void{
    this.selectedRequest=request;
    document.getElementById('modal')?.classList.toggle('is-active');
  }

  closeModalTab():void{
    document.getElementById('modal')?.classList.toggle('is-active');
  }

  rejectRequest():void{
    if (this.comment==''){
      alert('Insert your explanation');
      return;
    }
    this.requestService.rejectRequest(this.selectedRequest.id,this.comment).subscribe(data=>{
      if (data==false){
        alert('Failure');
      }else{
        this.removeRequestFromFront();
        alert('Successfully approved');
      }
    });
    document.getElementById('modal')?.classList.toggle('is-active');
  }
  
  
}
