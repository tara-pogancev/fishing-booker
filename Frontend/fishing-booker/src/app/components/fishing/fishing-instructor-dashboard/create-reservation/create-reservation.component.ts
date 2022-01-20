import { Component, OnInit } from '@angular/core';
import { Adventure } from '../../../../model/adventure-model';
import { Client } from '../../../../model/client-model';
import { InstructorNewReservation } from '../../../../model/instructor-new-reservation';
import { Utility } from '../../../../model/utility-model';
import { AdvetnureService } from '../../../../service/adventure-service';
import { ClientService } from '../../../../service/client.service';

@Component({
  selector: 'app-create-reservation',
  templateUrl: './create-reservation.component.html',
  styleUrls: ['./create-reservation.component.css']
})
export class CreateReservationComponent implements OnInit {

  constructor(private clientService:ClientService,private adventureService:AdvetnureService) { }

  clients:Client[]=[];
  adventures:any=[];
  validForm:boolean=true;
  reservation:InstructorNewReservation=new InstructorNewReservation();
  adventure:Adventure[]=[];
  startDate:Date=new Date();
  endDate:Date=new Date();
  utilities:Utility[]=[];
  ngOnInit(): void {
    this.adventureService.getInstructorAdventures().subscribe((data:any)=>this.adventures=data);
    this.clientService.getClientsWithReservationInTheMoment().subscribe(data=>{
    this.clients=data;
    })
  }

  validateForm():void{
    this.validForm=true;
    this.reservation.startDate=new Date(this.startDate).getTime()/1000;
    this.reservation.endDate=new Date(this.endDate).getTime()/1000;
    if (this.reservation.clientId==-1 || this.reservation.adventureId==-1 || this.reservation.endDate==-1 || 
      this.reservation.startDate==-1 || this.reservation.startDate>this.reservation.endDate 
      || this.reservation.startDate<new Date().getTime()/1000 ||
       this.reservation.endDate<new Date().getTime()/1000 ){
      this.validForm=false;
      return;
    }
    this.adventureService.createAdventureReservation(this.reservation).subscribe(data=>{
      alert("Reservation successfully created!");
      },
      error=>{
        alert('Reservation in selected date already exists or fishing instructor is not available')
      }
    );
  }

  removeService(service:Utility){
    const index=this.reservation.utilities.indexOf(service);
    this.reservation.utilities.splice(index,1);
  }

  adventureChosen(event:any){
    this.adventureService.getAdventureUtilities(this.reservation.adventureId).subscribe(data=>{
      this.reservation.utilities.length=0;
      this.reservation.utilities=data;
    })
  }

}
