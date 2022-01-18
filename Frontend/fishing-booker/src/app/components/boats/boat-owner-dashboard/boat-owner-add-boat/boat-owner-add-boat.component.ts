import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-boat-owner-add-boat',
  templateUrl: './boat-owner-add-boat.component.html',
  styleUrls: ['./boat-owner-add-boat.component.css']
})
export class BoatOwnerAddBoatComponent implements OnInit {
  @Input() boatOwner: any;
  
  constructor() { }

  ngOnInit(): void {
  }

}
