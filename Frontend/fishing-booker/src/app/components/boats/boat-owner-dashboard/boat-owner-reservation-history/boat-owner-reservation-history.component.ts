import { Component, Input, OnInit } from '@angular/core';
import { Boat } from 'src/app/model/boat-model';
import { Client } from 'src/app/model/client-model';
import { ReservationModel } from 'src/app/model/reservation-model';
import { BoatOwnerService } from 'src/app/service/boat-owner.service';
import { BoatService } from 'src/app/service/boat.service';
import { ClientService } from 'src/app/service/client.service';

@Component({
  selector: 'app-boat-owner-reservation-history',
  templateUrl: './boat-owner-reservation-history.component.html',
  styleUrls: ['./boat-owner-reservation-history.component.css']
})
export class BoatOwnerReservationHistoryComponent implements OnInit {
  @Input() boatOwner: any;
  reservations:ReservationModel[]=[];
  clients: Client[] = [];
  boats: Boat[] = [];

  constructor(private boatOwnerService: BoatOwnerService, private clientService: ClientService, private boatService: BoatService) { }

  ngOnInit(): void {
    this.boatOwnerService.getPastReservations(this.boatOwner.id).subscribe((data) => {
      this.boatService.findByBoatOwnerId(this.boatOwner.id).subscribe((data1) => {
        this.reservations = data;
        this.boats = data1;
        for (let r of this.reservations) {
          let c = this.boats.find(x => x.id == r.entityId);
          if (c == undefined)
            r.entityName = '';
          else
            r.entityName = c.name;
        }
        this.getClientsFromReservations();
      });
    });
  }

  getClientsFromReservations() {
    for (let r of this.reservations) {
      if (!this.userInClients(r.userId)) {
        this.clientService.getClient(r.userId).subscribe((data) => {
          this.clients.push(data);
        });
      }
    }
  }

  userInClients(userId: number) {
    for (let c of this.clients) {
      if (userId == Number(c.id)) {
        return true;
      }
    }
    return false;
  }

  getClient(userId: number) {
    for (let c of this.clients) {
      if (userId == Number(c.id)) {
        return c;
      }
    }
    return new Client;
  }

  openModalUser(){
    document.getElementById('modalUser')?.classList.toggle('is-active');
  }

  closeModalUser(){
    document.getElementById('modalUser')?.classList.toggle('is-active');
  }

  createReport(reservation: ReservationModel) {
    window.location.href='boat-reservation-report/'+reservation.id;
  }

}
