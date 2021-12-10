import { Component, OnInit } from '@angular/core';
import { CottageOwnerService } from 'src/app/service/cottage-owner.service';

@Component({
  selector: 'app-cottage-owner-list',
  templateUrl: './cottage-owner-list.component.html',
  styleUrls: ['./cottage-owner-list.component.css']
})
export class CottageOwnerListComponent implements OnInit {

  cottageOwners:any=[];
  constructor(private cottageOwnerService:CottageOwnerService) { }

  ngOnInit(): void {
    this.cottageOwnerService.getEnabledOwners().subscribe((data:any)=>this.cottageOwners=data);
  }

  deleteOwner(cottageOwner:any){
    this.cottageOwnerService.deleteCottageOwner(cottageOwner.id).subscribe();
    const index=this.cottageOwners.indexOf(cottageOwner);
    this.cottageOwners.splice(index,1);
  }

}
