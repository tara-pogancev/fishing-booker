import { Component, Input, OnInit } from '@angular/core';
import { Adventure } from 'src/app/model/adventure-model';
import { Client } from 'src/app/model/client-model';
import { AdvetnureService } from 'src/app/service/adventure-service';

@Component({
  selector: 'client-fishing-reservations',
  templateUrl: './fishing-reservations.component.html',
  styleUrls: ['./fishing-reservations.component.css'],
})
export class FishingReservationsComponent implements OnInit {
  @Input() user: Client = new Client();
  adventures: Adventure[] = [];

  constructor(private adventureService: AdvetnureService) {}

  ngOnInit(): void {
    this.adventureService.findAll().subscribe((data) => {
      this.adventures = data;
    });
  }
}
