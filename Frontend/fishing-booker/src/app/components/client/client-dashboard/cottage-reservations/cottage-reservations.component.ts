import { Component, Input, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';
import { Cottage } from 'src/app/model/cottage-model';
import { CottageService } from 'src/app/service/cottage.service';

@Component({
  selector: 'client-cottage-reservations',
  templateUrl: './cottage-reservations.component.html',
  styleUrls: ['./cottage-reservations.component.css'],
})
export class CottageReservationsComponent implements OnInit {
  @Input() user: Client = new Client();
  cottages: Cottage[] = [];

  constructor(private cottageService: CottageService) {}

  ngOnInit(): void {
    this.cottageService.findAll().subscribe((data) => {
      this.cottages = data;
    });
  }
}
