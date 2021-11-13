import { Component, Input, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';

@Component({
  selector: 'client-special-offers',
  templateUrl: './special-offers.component.html',
  styleUrls: ['./special-offers.component.css'],
})
export class SpecialOffersComponent implements OnInit {
  @Input() user: Client = new Client();

  constructor() {}

  ngOnInit(): void {}
}
