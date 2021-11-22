import { Component, OnInit } from '@angular/core';
import { Adventure } from 'src/app/model/adventure-model';
import { AdvetnureService } from 'src/app/service/adventure-service';

@Component({
  selector: 'app-browse-fishing',
  templateUrl: './browse-fishing.component.html',
  styleUrls: ['./browse-fishing.component.css']
})
export class BrowseFishingComponent implements OnInit {
  adventures: Adventure[] = [];

  constructor(private adventureService: AdvetnureService) { }

  ngOnInit(): void {
    this.adventureService.findAll().subscribe((data) => {
      this.adventures = data;
    });
  }

}
