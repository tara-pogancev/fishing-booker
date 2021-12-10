import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-cottage-owner-personal-info',
  templateUrl: './cottage-owner-personal-info.component.html',
  styleUrls: ['./cottage-owner-personal-info.component.css']
})
export class CottageOwnerPersonalInfoComponent implements OnInit {
  @Input() cottageOwner: any;

  constructor() { }

  ngOnInit(): void {
  }

}
