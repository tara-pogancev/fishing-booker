import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserModel } from '../../model/user-model';
import { RegistrationService } from '../../service/registration.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  user = new UserModel();
  validPassword: boolean = true;
  validEmail: boolean = true;
  validForm: boolean = true;
  selectedClient: boolean = true;

  loader = false;

  constructor(
    private registrationService: RegistrationService,
    private route: Router
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
            this.loader = true;
            this.registerUser();
          }
        });
    }
  }

  registerUser() {
    this.registrationService.register(this.user).subscribe((data) => {
      this.loader = false;
      if (data != null) {
        this.route.navigate(['thank-you-registration']);
      } else {
        this.route.navigate(['error']);
      }
    });
  }

  changeRegistrationType() {
    if (this.user.role === 'Registered Client') {
      this.selectedClient = true;
    } else {
      this.selectedClient = false;
    }
  }
}
