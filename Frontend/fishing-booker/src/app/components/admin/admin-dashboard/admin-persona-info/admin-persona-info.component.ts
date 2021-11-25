import { Component, Input, OnInit } from '@angular/core';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-admin-persona-info',
  templateUrl: './admin-persona-info.component.html',
  styleUrls: ['./admin-persona-info.component.css']
})
export class AdminPersonaInfoComponent implements OnInit {

  admin:any;
  fullAddress:string='';
  constructor(private adminService: AdminService) { }

  ngOnInit(): void {
    this.adminService.getCurrentClient().subscribe((data: any) => {
      this.admin = data;
      this.fullAddress=this.admin.userAddress.city+' '+this.admin.userAddress.street;
      console.log(data);
    });
    
  }

}
