import { Component, OnInit, Input } from '@angular/core';
import { Client } from 'src/app/model/client-model';
import { Cottage } from 'src/app/model/cottage-model';
import { ReservationModel } from 'src/app/model/reservation-model';
import { ClientService } from 'src/app/service/client.service';
import { CottageOwnerService } from 'src/app/service/cottage-owner.service';
import { CottageService } from 'src/app/service/cottage.service';

@Component({
  selector: 'app-cottage-owner-reservation-history',
  templateUrl: './cottage-owner-reservation-history.component.html',
  styleUrls: ['./cottage-owner-reservation-history.component.css']
})
export class CottageOwnerReservationHistoryComponent implements OnInit {
  @Input() cottageOwner: any;
  reservations:ReservationModel[]=[];
  clients: Client[] = [];
  cottages: Cottage[] = [];

  constructor(private cottageOwnerService: CottageOwnerService, private clientService: ClientService, private cottageService: CottageService) { }

  ngOnInit(): void {
    this.cottageOwnerService.getPastReservations(this.cottageOwner.id).subscribe((data) => {
      this.cottageService.findByCottageOwnerId(this.cottageOwner.id).subscribe((data1) => {
        this.reservations = data;
        this.cottages = data1;
        for (let r of this.reservations) {
          let c = this.cottages.find(x => x.id == r.entityId);
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
    window.location.href='cottage-reservation-report/'+reservation.id;
  }

}
