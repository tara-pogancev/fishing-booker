import { Component, Input, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';
import { ClientService } from 'src/app/service/client.service';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css'],
})
export class SettingsComponent implements OnInit {
  @Input() user: Client = new Client();
  validPassword: boolean = true;
  validForm: boolean = true;

  constructor(private clientService: ClientService) {}

  ngOnInit(): void {
    this.user.password = '';
    this.user.passwordConfirm = '';
  }

  validatePassword() {
    this.validPassword = this.user.password == this.user.passwordConfirm;
  }

  validateForm() {
    this.validForm =
      this.user.name != '' &&
      this.user.lastName != '' &&
      this.user.phone != '' &&
      this.user.street != '' &&
      this.user.city != '' &&
      this.user.country != '' &&
      this.user.password != '' &&
      this.user.passwordConfirm != '' &&
      this.validPassword;

    if (this.validForm == true) {
      this.updateClient();
    }
  }

  updateClient() {
    this.clientService.updateClientData(this.user).subscribe((data) => {
      console.log(data);
      window.location.href = '/client-db';
      this.user.passwordConfirm = '';
      alert('Changes saved!');
    });
  }
}
