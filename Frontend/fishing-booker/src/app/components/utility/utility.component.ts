import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'utility',
  templateUrl: './utility.component.html',
  styleUrls: ['./utility.component.css'],
})
export class UtilityComponent implements OnInit {
  @Input() utility: string = 'utility';
  @Input() price: number = 40;

  constructor() {}

  ngOnInit(): void {}
}
