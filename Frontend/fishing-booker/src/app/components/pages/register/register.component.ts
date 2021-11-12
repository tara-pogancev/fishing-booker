import { ThrowStmt } from '@angular/compiler';
import { Component, OnInit, ɵgetInjectableDef } from '@angular/core';
import { Router } from '@angular/router';
import { UserModel } from '../../../model/user-model';
import { RegistrationService } from '../../../service/registration.service';

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
            this.registerUser();
          }
        });
    }
  }

  registerUser() {
    this.registrationService
      .register(this.user)
      .subscribe((data) => console.log(data));
    this.route.navigate(['thank-you-registration']);
  }
}
