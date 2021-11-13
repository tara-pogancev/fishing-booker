import { Component, Input, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';

@Component({
  selector: 'client-past-cottage',
  templateUrl: './past-cottage.component.html',
  styleUrls: ['./past-cottage.component.css']
})
export class PastCottageComponent implements OnInit {
  @Input() user: Client = new Client();

  constructor() { }

  ngOnInit(): void {
  }

}
