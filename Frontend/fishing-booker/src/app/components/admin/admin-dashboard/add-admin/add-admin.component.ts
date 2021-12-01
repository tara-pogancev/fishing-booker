import { Component, OnInit } from '@angular/core';
import { UserModel } from 'src/app/model/user-model';
import { AdminService } from 'src/app/service/admin.service';
import { RegistrationService } from 'src/app/service/registration.service';

@Component({
  selector: 'app-add-admin',
  templateUrl: './add-admin.component.html',
  styleUrls: ['./add-admin.component.css']
})
export class AddAdminComponent implements OnInit {

  user = new UserModel();
  validPassword: boolean = true;
  validEmail: boolean = true;
  validForm: boolean = true;
  selectedClient:boolean=true;

  constructor(
    private adminService: AdminService,private registrationService: RegistrationService
    
  ) {}

  ngOnInit(): void {}

  validatePassword() {
    this.validPassword = this.user.password == this.user.passwordConfirm;
  }

  validateForm() {
    this.validForm =
      this.user.name != '' &&
      this.user.lastName != '' &&
      this.user.email != '' &&
      this.user.phone != '' &&
      this.user.street != '' &&
      this.user.city != '' &&
      this.user.country != '' &&
      this.user.password != '' &&
      this.user.passwordConfirm != '' &&
      this.validPassword;

    if (this.validForm) {
      this.registrationService
        .validateEmail(this.user.email)
        .subscribe((data) => {
          this.validEmail = data;

          if (this.validEmail) {
            this.registerUser();
          }
        });
    }
  }

  registerUser() {
    this.
      adminService.createAdmin(this.user)
      .subscribe((data) => console.log(data));
    alert('Administrator successfully created');
    window.location.href='admin-db';
  }

  

}
