import { Component, Input, OnInit } from '@angular/core';
import { AdventureQuickReservation } from 'src/app/model/adventure-quick-reservation-model';
import { Cottage } from 'src/app/model/cottage-model';
import { Utility } from 'src/app/model/utility-model';
import { CottageOwnerService } from 'src/app/service/cottage-owner.service';
import { CottageService } from 'src/app/service/cottage.service';
import { UtilityService } from 'src/app/service/utility.service';

@Component({
  selector: 'app-cottage-owner-quick-reservations',
  templateUrl: './cottage-owner-quick-reservations.component.html',
  styleUrls: ['./cottage-owner-quick-reservations.component.css']
})
export class CottageOwnerQuickReservationsComponent implements OnInit {
  @Input() cottageOwner: any;
  cottages:Cottage[]=[];
  utilities:Utility[]=[];
  newService:string='';
  price:number=0;
  selectedService='';
  //selectedAdventureId:number=-1;
  chosenUtilities:Utility[]=[];
  reservation:AdventureQuickReservation=new AdventureQuickReservation();
  validForm=true;

  constructor(private utilityService:UtilityService, private cottageService:CottageService) { 
  }

  ngOnInit(): void {
    this.utilityService.getUtilities().subscribe((data:any)=>this.utilities=data);
    this.cottageService.findByCottageOwnerId(this.cottageOwner.id).subscribe((data:any)=>this.cottages=data);
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
      this.reservation.actionStart=new Date(this.reservation.actionStart);
      this.reservation.actionEnd=new Date(this.reservation.actionEnd);
      this.cottageService.createCottageQuickReservation(this.reservation).subscribe(data=>{
        if (data!=-1){
          alert('Cottage quick reservation successfully added!');
        }else{
          alert('Reservation in selected date already exist');
        }
      });
      this.reservation=new AdventureQuickReservation();
    }else{
      this.validForm=false;
    }
  }

}
