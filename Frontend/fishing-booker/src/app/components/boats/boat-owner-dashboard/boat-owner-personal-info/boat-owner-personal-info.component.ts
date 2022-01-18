import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-boat-owner-personal-info',
  templateUrl: './boat-owner-personal-info.component.html',
  styleUrls: ['./boat-owner-personal-info.component.css']
})
export class BoatOwnerPersonalInfoComponent implements OnInit {
  @Input() boatOwner: any;

  constructor() { }

  ngOnInit(): void {
  }

}
