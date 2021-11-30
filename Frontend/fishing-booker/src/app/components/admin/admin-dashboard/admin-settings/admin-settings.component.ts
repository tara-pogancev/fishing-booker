import { Component, Input, OnInit } from '@angular/core';
import { AdminService } from 'src/app/service/admin.service';
import { ClientService } from 'src/app/service/client.service';
import { Client } from 'src/app/model/client-model';

@Component({
  selector: 'app-admin-settings',
  templateUrl: './admin-settings.component.html',
  styleUrls: ['./admin-settings.component.css']
})
export class AdminSettingsComponent implements OnInit {

  
  admin: any = {};
  validPassword: boolean = true;
  validForm: boolean = true;
  city: string='';
  street:string='';
  newAdmin:Client=new Client();
  passwordConfirm='';
  country='';
  constructor(private adminService:AdminService) { }

  ngOnInit(): void {
    this.adminService.getCurrentAdmin().subscribe((data: any) => {
      this.admin = data;
      this.admin.password = '';
      this.admin.passwordConfirm = '';
      this.city=this.admin.userAddress.city;
      this.street=this.admin.userAddress.street;
      this.passwordConfirm='';
      this.country=this.admin.userAddress.country;
    });
  }

  validatePassword() {
    this.validPassword = this.admin.password == this.passwordConfirm;
  }

  validateForm() {
    this.validForm =
      this.admin.name != '' &&
      this.admin.lastName != '' &&
      this.admin.phone != '' &&
      this.admin.street != '' &&
      this.admin.city != '' &&
      this.admin.country != '' &&
      this.admin.password != '' &&
      this.passwordConfirm != '' &&
      this.validPassword;

    if (this.validForm == true) {
      this.updateAdmin();
    }

  }

  updateAdmin() {
    this.newAdmin.id=this.admin.id;
    this.newAdmin.city=this.city;
    this.newAdmin.country=this.country;
    this.newAdmin.name=this.admin.name;
    this.newAdmin.lastName=this.admin.lastName;
    this.newAdmin.phone=this.admin.phone;
    this.newAdmin.password=this.passwordConfirm;
    this.newAdmin.street=this.street;
    this.adminService.updateClientData(this.newAdmin).subscribe((data) => {
      console.log(data);
      window.location.href = '/admin-db';
      this.passwordConfirm = '';
      alert('Changes saved!');
    });
  }

}
