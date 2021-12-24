import { Component, OnInit } from '@angular/core';
import { DeleteAccountRequest } from '../../../../model/delete-request-model';
import { DeleteAccountService } from '../../../../service/delete-account.service';

@Component({
  selector: 'app-delete-account-requests',
  templateUrl: './delete-account-requests.component.html',
  styleUrls: ['./delete-account-requests.component.css']
})
export class DeleteAccountRequestsComponent implements OnInit {

  requests:DeleteAccountRequest[]=[];
  selectedRequest: DeleteAccountRequest=new DeleteAccountRequest();
  comment:string='';
  acceptClicked=true;
  
  constructor(private deleteAccountService:DeleteAccountService) { }
  activeTab:string='';
  ngOnInit(): void {
    this.deleteAccountService.getRequests().subscribe(requests=>this.requests=requests) 
  }
  acceptButtonClicked(request:DeleteAccountRequest){
    this.acceptClicked=true;
    this.openModalTab(request);
    this.selectedRequest=request;
  }

  rejectButtonClicked(request:DeleteAccountRequest){
    this.acceptClicked=false;
    this.openModalTab(request);
    this.selectedRequest=request;
  }

  sendMail(){
    if (this.acceptClicked){
      this.acceptRequest();
    }else{
      this.rejectRequest();
    }
    this.closeModalTab();
  }
  acceptRequest():void{
    if (this.comment==''){
      alert('Insert your explanation');
      return;
    }
    this.deleteAccountService.approveRequest(this.selectedRequest.id,this.comment);
    alert('Success');
  }

  openModalTab(request:DeleteAccountRequest):void{
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
    this.deleteAccountService.rejectRequest(this.selectedRequest.id,this.comment);
    alert('Success');
  }

}
