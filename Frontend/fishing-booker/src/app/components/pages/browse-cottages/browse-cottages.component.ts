import { Component, OnInit } from '@angular/core';
import { Cottage } from 'src/app/model/cottage-model';
import { CottageService } from 'src/app/service/cottage.service';

@Component({
  selector: 'app-browse-cottages',
  templateUrl: './browse-cottages.component.html',
  styleUrls: ['./browse-cottages.component.css'],
})
export class BrowseCottagesComponent implements OnInit {
  cottages: Cottage[] = [];

  constructor(private cottageService: CottageService) {}

  ngOnInit(): void {
    this.cottageService.findAll().subscribe((data) => {
      this.cottages = data;
    });
  }
}
