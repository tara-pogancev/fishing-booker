import { Component, OnInit } from '@angular/core';
import { UtilityService } from 'src/app/service/utility.service';

import { Utility } from 'src/app/model/utility-model';
import { AdvetnureService } from '../../../../service/adventure-service';
import { AdventureQuickReservation } from '../../../../model/adventure-quick-reservation-model';

@Component({
  selector: 'app-quick-reservations',
  templateUrl: './quick-reservations.component.html',
  styleUrls: ['./quick-reservations.component.css']
})
export class QuickReservationsComponent implements OnInit {

  adventures:any=[];
  utilities:Utility[]=[];
  newService:string='';
  price:number=0;
  selectedService='';
  selectedAdventureId:number=-1;
  chosenUtilities:Utility[]=[];
  reservation:AdventureQuickReservation=new AdventureQuickReservation();
  validForm=true;
  constructor(private utilityService:UtilityService, private adventureService:AdvetnureService) { 
  }

  ngOnInit(): void {
    this.utilityService.getUtilities().subscribe((data:any)=>this.utilities=data);
    this.adventureService.getInstructorAdventures().subscribe((data:any)=>this.adventures=data);
  }

  addService(){
    let serviceName="";
    let servicePrice=0;
    if (this.selectedService==""){
        if (this.newService=="" || this.price<0){
          alert("Enter service name or chose existing one");
          return;
        }
        serviceName=this.newService;
        servicePrice=this.price;
    }else{
      if (this.price<0){
        alert("Enter price of service");
        return;
      }
      servicePrice=this.price;
      serviceName=this.selectedService;
    }
    let existingUtility=this.getUtility(serviceName);
    let utility=new Utility();
    if (existingUtility==null){
      utility.name=serviceName;
      utility.price=servicePrice;
    }else{
      utility.name=existingUtility.name;
      utility.utilityId=existingUtility.id;
      utility.price=servicePrice;
    }
    
    this.newService='';
    this.price=0;
    this.chosenUtilities.push(utility);
  }

  getUtility( serviceName: string){
    for (let utility of this.utilities){
        if (utility.name==serviceName){
          return utility;
        }
    }
    return null;
  }

  removeService(service:Utility){
    const index=this.chosenUtilities.indexOf(service);
    this.chosenUtilities.splice(index,1);
  }


  validateForm(){
    if (this.reservation.actionEnd!=new Date() && this.reservation.actionStart != new Date() && this.reservation.guestLimit>0 && this.reservation.price>0 ){
      this.validForm=true;
      this.reservation.adventureUtilityDtoList=this.chosenUtilities;
      this.adventureService.createAdventureQuickReservation(this.reservation).subscribe();
      alert('Adventure successfully added!');
      this.reservation=new AdventureQuickReservation();
    }else{
      this.validForm=false;
    }
  }




}
