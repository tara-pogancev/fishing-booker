import { Component, OnInit } from '@angular/core';
import { ClientService } from 'src/app/service/client.service';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent implements OnInit {

  clients:any=[];
  constructor(private clientService:ClientService) {

   }
  
  ngOnInit(): void {
    this.clientService.loadEnabledClients().subscribe(clients=>this.clients=clients);
  }

  deleteClient(client:any){
    this.clientService.deleteClient(client.id).subscribe();

    const index = this.clients.indexOf(client);
    this.clients.splice(index, 1);
    
  }

}
