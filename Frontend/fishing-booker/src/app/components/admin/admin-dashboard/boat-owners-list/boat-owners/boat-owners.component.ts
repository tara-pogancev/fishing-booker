import { Component, OnInit } from '@angular/core';
import { BoatOwnerService } from 'src/app/service/boat-owner.service';

@Component({
  selector: 'app-boat-owners',
  templateUrl: './boat-owners.component.html',
  styleUrls: ['./boat-owners.component.css']
})
export class BoatOwnersComponent implements OnInit {

  boatOwners:any=[];
  constructor(private boatOwnerService:BoatOwnerService) { }

  ngOnInit(): void {
    this.boatOwnerService.getEnabledOwners().subscribe((data:any)=>this.boatOwners=data);
  }

  deleteOwner(boatOwner:any){
    this.boatOwnerService.deleteBoatOwner(boatOwner.id).subscribe();
    const index=this.boatOwners.indexOf(boatOwner);
    this.boatOwners.splice(index,1);
  }

}
