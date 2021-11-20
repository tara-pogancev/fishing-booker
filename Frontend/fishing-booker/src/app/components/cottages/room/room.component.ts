import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css'],
})
export class RoomComponent implements OnInit {
  @Input() beds: number = 0;
  constructor() {}

  ngOnInit(): void {}
}
