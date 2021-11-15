import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-boat-page',
  templateUrl: './boat-page.component.html',
  styleUrls: ['./boat-page.component.css'],
})
export class BoatPageComponent implements OnInit {
  fullAddress: string = 'Bulevar oslobodjenja 3, Novi Sad, Serbia';

  constructor() {}

  ngOnInit(): void {}
}
