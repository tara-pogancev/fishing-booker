import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-cottage-owner-reservation-history',
  templateUrl: './cottage-owner-reservation-history.component.html',
  styleUrls: ['./cottage-owner-reservation-history.component.css']
})
export class CottageOwnerReservationHistoryComponent implements OnInit {
  @Input() cottageOwner: any;

  constructor() { }

  ngOnInit(): void {
  }

  openModalUser(){
    document.getElementById('modalUser')?.classList.toggle('is-active');
  }

  closeModalUser(){
    document.getElementById('modalUser')?.classList.toggle('is-active');
  }

  createRapport() {
    alert("Rapport Creation");
  }

}
