import { Component, Input, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';

@Component({
  selector: 'client-delete-account',
  templateUrl: './client-delete-account.component.html',
  styleUrls: ['./client-delete-account.component.css'],
})
export class ClientDeleteAccountComponent implements OnInit {
  @Input() user: Client = new Client();

  constructor() {}

  ngOnInit(): void {}
}
