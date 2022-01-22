import { Component, OnInit } from '@angular/core';
import { BoatService } from 'src/app/service/boat.service';

@Component({
  selector: 'app-boat-list',
  templateUrl: './boat-list.component.html',
  styleUrls: ['./boat-list.component.css']
})
export class BoatListComponent implements OnInit {

  boats:any=[];
  constructor(private boatService:BoatService) { }

  ngOnInit(): void {
    this.boatService.getBoats().subscribe((data:any)=>this.boats=data);
  }

  deleteBoat(boat:any){
    this.boatService.deleteBoatByAdmin(boat.id).subscribe();
    const index=this.boats.indexOf(boat);
    this.boats.splice(index,1);
  }

}
