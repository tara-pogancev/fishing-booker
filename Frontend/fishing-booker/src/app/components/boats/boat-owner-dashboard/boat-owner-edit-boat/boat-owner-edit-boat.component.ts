import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { BoatType } from 'src/app/model/boat-type-model';
import { CreateBoatModel } from 'src/app/model/create-boat-model';
import { NavigationalEquipment } from 'src/app/model/naviagational-equipment-model';
import { BoatService } from 'src/app/service/boat.service';
import { UtilityService } from 'src/app/service/utility.service';
import { Rule } from 'src/app/model/rule-model';
import { Image } from 'src/app/model/image-model';
import { Utility } from 'src/app/model/utility-model';

@Component({
  selector: 'app-boat-owner-edit-boat',
  templateUrl: './boat-owner-edit-boat.component.html',
  styleUrls: ['./boat-owner-edit-boat.component.css']
})
export class BoatOwnerEditBoatComponent implements OnInit {
  @Input() boatOwner: any;

  boat:CreateBoatModel=new CreateBoatModel();
  myForm = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(3)]),
    file: new FormControl('', [Validators.required]),
    fileSource: new FormControl('', [Validators.required])
  });
  rule:string='';
  room:string='';
  selectedService='';
  newService='';
  price=0;
  validForm=true;
  utilities:any=[];
  boatTypes:BoatType[];
  selectedBoatType='';
  selectedNavEq='';
  navigationalEquipment: NavigationalEquipment[];
  id: any = -1;

  constructor(private http: HttpClient,private utilityService:UtilityService,private boatService:BoatService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = +this.route.snapshot.paramMap.get('id')!;
    this.utilityService.getUtilities().subscribe((data:any)=>this.utilities=data);
    this.boatService.getBoatTypes().subscribe((data:any)=>this.boatTypes=data);
    this.boatService.getNavigationalEquipment().subscribe((data:any)=>this.navigationalEquipment=data);
    this.boatService.getBoat(this.id).subscribe(data => {
            this.boat = data;
            this.selectedBoatType = this.boat.boatType;
            console.log(this.boat);
          });
  }

  get f(){
    return this.myForm.controls;
  }

  onFileChange(event:any) {
    if (event.target.files && event.target.files[0]) {
        var filesAmount = event.target.files.length;
        for (let i = 0; i < filesAmount; i++) {
                var reader = new FileReader();
                var name=event.target.files[0].name;
                reader.onload = (event:any) => {
                  console.log(event.target.result);
                   let image:Image=new Image();
                   image.content=event.target.result;
                   image.extension=this.getExtension(event.target.result);
                   image.name=name;
                   this.boat.images.push(image); 
                   this.myForm.patchValue({
                      fileSource: this.boat.images
                   });
                }
                reader.readAsDataURL(event.target.files[i]);
        }
    }
  }

  getExtension(srcData:string){
    var parts=srcData.split(",");
    let extension=parts[0].split(";")[0].split("/")[1]; 
    return extension;
}

  deleteImage(image:Image){
    const index=this.boat.images.indexOf(image);
    this.boat.images.splice(index,1);
  }

  getUtility( serviceName: string){
    for (let utility of this.utilities){
        if (utility.name==serviceName){
          return utility;
        }
    }
    return null;
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
      utility.id = existingUtility.id;
    }
    
    this.newService='';
    this.price=0;
    this.boat.additionalServices.push(utility);
  }

  removeService(service:Utility){
    const index=this.boat.additionalServices.indexOf(service);
    this.boat.additionalServices.splice(index,1);
  }

  addNavigationalEquipment(){
    if (this.selectedNavEq==''){
      alert('Enter navigational equipment');
      return;
    }
    this.boat.navigationalEquipments.push(this.selectedNavEq);
    this.selectedNavEq='';
  }

  removeNavigationalEquipment(navEquipment:string){
    let index=0;
    for (let navEq of this.boat.navigationalEquipments){
        if (navEq==navEquipment){
          this.boat.navigationalEquipments.splice(index,1);
          return;
        }
        index++;
    }
  }

  addConductRule(){
    if (this.rule==''){
      alert('Enter conduct rule name');
      return;
    }
    this.boat.rules.push(new Rule(-1,this.rule));
    this.rule='';
  }

  removeConductRule(rule:string){
    let index=0;
    for (let conductRule of this.boat.rules){
      if (conductRule.ruleDescription==rule){
        this.boat.rules.splice(index,1);
      }
      index++;
    }
  }

  validateForm(){
    if (this.boat.name!="" && this.boat.guestLimit>0 && this.boat.description!="" && this.boat.city!="" && this.boat.country!="" && this.boat.price>=0 && this.boat.street!=""
    && this.boat.maxSpeed>=0 && this.boat.numberOfEngines>=0 && this.boat.enginePower>=0 && this.boat.boatLength>=0
    && this.boat.cancellationPercentageKeep>=0 && this.boat.cancellationPercentageKeep<=100 && this.selectedBoatType!=""){
      this.validForm=true;
      this.boat.boatType = this.selectedBoatType;
      this.boatService.createBoat(this.boat).subscribe();
      alert('Boat successfully changed!');
      window.location.href = "preview-boat/" + this.boat.id;
    }else{
      this.validForm=false;
    }
  }

}
