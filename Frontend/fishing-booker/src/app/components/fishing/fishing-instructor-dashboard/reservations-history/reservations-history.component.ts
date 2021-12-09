import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-reservations-history',
  templateUrl: './reservations-history.component.html',
  styleUrls: ['./reservations-history.component.css']
})
export class ReservationsHistoryComponent implements OnInit {

  constructor() { }

  openModalUser(){
    document.getElementById('modalUser')?.classList.toggle('is-active');
  }

  closeModalUser(){
    document.getElementById('modalUser')?.classList.toggle('is-active');
  }

  openModalReport(){
    document.getElementById('modalReport')?.classList.toggle('is-active');
  }

  closeModalReport(){
    document.getElementById('modalReport')?.classList.toggle('is-active');
  }

  ngOnInit(): void {
  }

}
