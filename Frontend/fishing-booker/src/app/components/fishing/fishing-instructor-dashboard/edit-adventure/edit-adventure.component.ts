import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Adventure } from 'src/app/model/adventure-model';
import { AdvetnureService } from 'src/app/service/adventure-service';
import { FishingInstructorService } from 'src/app/service/fishing-instructor.service';
import { ImageService } from 'src/app/service/image.service';

@Component({
  selector: 'app-edit-adventure',
  templateUrl: './edit-adventure.component.html',
  styleUrls: ['./edit-adventure.component.css']
})
export class EditAdventureComponent implements OnInit {

  id: number = 0;
  adventure: Adventure = new Adventure();
  navEquipment: string = '';
  image: any = 'assets/images/placeholder.jpg';
  editMode=true;
  adventureQuickReservations:any[]=[];
  loadData=false;


  constructor(
    private route: ActivatedRoute,
    private adventureService: AdvetnureService,
    private imageService: ImageService
  ) {}

  ngOnInit(): void {
    this.id = +this.route.snapshot.paramMap.get('id')!;

    this.adventureService.findById(this.id).subscribe((data) => {
      this.adventure = data;

      this.navEquipment = '';
      this.adventure.fishingEquipments.forEach((equipment) => {
        this.navEquipment += equipment.fishingEquipmentName + ', ';
      });
      this.navEquipment = this.navEquipment.slice(0, -2);
      if (this.navEquipment == '') this.navEquipment = 'None';
    });

    this.adventureService.getQuickReservationForCalendar(this.id).subscribe((data)=>{ this.adventureQuickReservations=data;console.log(this.adventureQuickReservations); this.loadData=true});
    
  }

  editAdventure(){
    location.href="edit-adventure/"+this.id;
  }

}
