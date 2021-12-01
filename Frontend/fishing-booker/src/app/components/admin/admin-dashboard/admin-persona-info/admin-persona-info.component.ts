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
  password="";
  passwordConfirm="";
  validPassword=true;

  constructor(private adminService: AdminService) { }

  ngOnInit(): void {
    this.adminService.getCurrentAdmin().subscribe((data: any) => {
      this.admin = data;
      this.fullAddress=this.admin.userAddress.city+' '+this.admin.userAddress.street;
      console.log(data);
      this.openModalTab();
    });
    
  }

  openModalTab():void{
    if (this.admin.firstTimeLoggedIn==true){
      document.getElementById('modal')?.classList.toggle('is-active');
    }
  }

  closeModalTab():void{
    if (this.validPassword){
      document.getElementById('modal')?.classList.toggle('is-active');
      this.adminService.changePassword(this.admin.id,this.password);
    }
    
  }

  validatePassword() {
    this.validPassword = this.password == this.passwordConfirm;
  }

}
