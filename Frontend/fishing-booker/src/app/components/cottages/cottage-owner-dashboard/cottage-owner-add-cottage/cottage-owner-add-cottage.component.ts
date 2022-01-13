import { Component, OnInit, Input } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CreateCottageModel } from 'src/app/model/create-cottage-model';
import { Image } from 'src/app/model/image-model';
import { Rule } from 'src/app/model/rule-model';
import { Utility } from 'src/app/model/utility-model';
import { UtilityService } from 'src/app/service/utility.service';
import { HttpClient } from '@angular/common/http';
import { CottageService } from 'src/app/service/cottage.service';
import { Room } from 'src/app/model/room-model';

@Component({
  selector: 'app-cottage-owner-add-cottage',
  templateUrl: './cottage-owner-add-cottage.component.html',
  styleUrls: ['./cottage-owner-add-cottage.component.css']
})
export class CottageOwnerAddCottageComponent implements OnInit {
  @Input() cottageOwner: any;

  cottage:CreateCottageModel=new CreateCottageModel();
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

  constructor(private http: HttpClient,private utilityService:UtilityService,private cottageService:CottageService) { }

  ngOnInit(): void {
    this.utilityService.getUtilities().subscribe((data:any)=>this.utilities=data);
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
                   this.cottage.images.push(image); 
                   this.myForm.patchValue({
                      fileSource: this.cottage.images
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
    const index=this.cottage.images.indexOf(image);
    this.cottage.images.splice(index,1);
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
    this.cottage.additionalServices.push(utility);
  }

  removeService(service:Utility){
    const index=this.cottage.additionalServices.indexOf(service);
    this.cottage.additionalServices.splice(index,1);
  }

  addRoom(){
    if (this.room==''){
      alert('Enter number of beds');
      return;
    }
    this.cottage.rooms.push(new Room(-1,Number(this.room)));
    this.room='';
  }

  removeRoom(r:number){
    let index=0;
    for (let room of this.cottage.rooms){
        if (room.numberOfBeds==r){
          this.cottage.rooms.splice(index,1);
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
    this.cottage.rules.push(new Rule(-1,this.rule));
    this.rule='';
  }

  removeConductRule(rule:string){
    let index=0;
    for (let conductRule of this.cottage.rules){
      if (conductRule.ruleDescription==rule){
        this.cottage.rules.splice(index,1);
      }
      index++;
    }
  }

  validateForm(){
    if (this.cottage.name!="" && this.cottage.guestLimit>0 && this.cottage.description!="" && this.cottage.city!="" && this.cottage.country!="" && this.cottage.price>=0 && this.cottage.street!=""){
      this.validForm=true;
      this.cottageService.createCottage(this.cottage).subscribe();
      alert('Cottage successfully added!');
      this.cottage=new CreateCottageModel();
    }else{
      this.validForm=false;
    }
  }

}
