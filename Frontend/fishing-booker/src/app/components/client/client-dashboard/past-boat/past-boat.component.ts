import { Component, Input, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';

@Component({
  selector: 'client-past-boat',
  templateUrl: './past-boat.component.html',
  styleUrls: ['./past-boat.component.css'],
})
export class PastBoatComponent implements OnInit {
  @Input() user: Client = new Client();

  constructor() {}

  ngOnInit(): void {}
}
