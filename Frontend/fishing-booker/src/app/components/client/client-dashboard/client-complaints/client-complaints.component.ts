import { Component, Input, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';

@Component({
  selector: 'client-complaints',
  templateUrl: './client-complaints.component.html',
  styleUrls: ['./client-complaints.component.css'],
})
export class ClientComplaintsComponent implements OnInit {
  @Input() user: Client = new Client();

  constructor() {}

  ngOnInit(): void {}
}
