import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-complaints',
  templateUrl: './admin-complaints.component.html',
  styleUrls: ['./admin-complaints.component.css'],
})
export class AdminComplaintsComponent implements OnInit {
  complaints: any = [];

  constructor() {}

  ngOnInit(): void {}
}
