import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Adventure } from 'src/app/model/adventure-model';
import { AdvetnureService } from 'src/app/service/adventure-service';
import { ImageService } from 'src/app/service/image.service';

@Component({
  selector: 'app-fishing-page',
  templateUrl: './fishing-page.component.html',
  styleUrls: ['./fishing-page.component.css'],
})
export class FishingPageComponent implements OnInit {
  id: number = 0;
  adventure: Adventure = new Adventure();
  navEquipment: string = '';
  image: any = 'assets/images/placeholder.jpg';

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
      this.adventure.navigationalEquipments.forEach((equipment) => {
        this.navEquipment += equipment + ', ';
      });
      this.navEquipment = this.navEquipment.slice(0, -2);
    });
  }
}
