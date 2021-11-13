import { Component, Input, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';

@Component({
  selector: 'client-past-fishing',
  templateUrl: './past-fishing.component.html',
  styleUrls: ['./past-fishing.component.css'],
})
export class PastFishingComponent implements OnInit {
  @Input() user: Client = new Client();

  constructor() {}

  ngOnInit(): void {}
}
