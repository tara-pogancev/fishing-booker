import { Component, Input, OnInit } from '@angular/core';
import { Boat } from 'src/app/model/boat-model';
import { BoatService } from 'src/app/service/boat.service';

@Component({
  selector: 'boat-card',
  templateUrl: './boat-card.component.html',
  styleUrls: ['./boat-card.component.css'],
})
export class BoatCardComponent implements OnInit {
  @Input() id: number = 0;
  @Input() img: string = '/assets/images/boat.jpg';
  boat: Boat = new Boat();

  constructor(private boatService: BoatService) {}

  ngOnInit(): void {
    this.boatService.findById(this.id).subscribe((data) => {
      this.boat = data;
    });
  }

  readMore() {
    window.location.href = 'boat/' + this.id;
  }
}
