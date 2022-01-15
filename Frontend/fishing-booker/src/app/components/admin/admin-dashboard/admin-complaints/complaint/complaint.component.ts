import { Component, OnInit } from '@angular/core';
import { ComplaintModel } from '../../../../../model/complaint-model';
import { ComplaintService } from '../../../../../service/complaint.service';

@Component({
  selector: 'app-complaint',
  templateUrl: './complaint.component.html',
  styleUrls: ['./complaint.component.css']
})
export class ComplaintComponent implements OnInit {

  complaints:ComplaintModel[]=[];
  selectedComplaint:ComplaintModel;
  comment:string='';
  constructor(private complaintService:ComplaintService) { }

  ngOnInit(): void {
    this.complaintService.getWaitingComplaints().subscribe(data=>{
      this.complaints=data;
    })
  }

  openModalTab(complaint:ComplaintModel):void{
    this.selectedComplaint=complaint;
    document.getElementById('modal')?.classList.toggle('is-active');
  }

  response():void{
    if (this.comment==''){
      alert('Insert your explanation');
      return;
    }
    this.complaintService.createResponse(this.comment,this.selectedComplaint.id).subscribe(data=>{
      if (data==false){
        alert('Response is already created!');
      }else{
        alert('Success!');
      }
      this.removeFromFrontend();
    });
    document.getElementById('modal')?.classList.toggle('is-active');
    
  }

  closeModalTab():void{
    document.getElementById('modal')?.classList.toggle('is-active');
  }

  removeFromFrontend(){
    const index=this.complaints.indexOf(this.selectedComplaint);
    this.complaints.splice(index,1);
  }

}
