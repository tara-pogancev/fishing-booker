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
  newUser: Client = new Client();
  validPassword: boolean = true;
  validForm: boolean = true;

  constructor(private clientService: ClientService) {}

  ngOnInit(): void {
    this.clientService.getCurrentClient().subscribe((data: Client) => {
      this.newUser = data;
      this.newUser.password = '';
      this.newUser.passwordConfirm = '';
    });
  }

  validatePassword() {
    this.validPassword = this.newUser.password == this.newUser.passwordConfirm;
  }

  validateForm() {
    this.validForm =
      this.newUser.name != '' &&
      this.newUser.lastName != '' &&
      this.newUser.phone != '' &&
      this.newUser.street != '' &&
      this.newUser.city != '' &&
      this.newUser.country != '' &&
      this.newUser.password != '' &&
      this.newUser.passwordConfirm != '' &&
      this.validPassword;

    if (this.validForm == true) {
      this.updateClient();
    }
  }

  updateClient() {
    this.clientService.updateClientData(this.newUser).subscribe((data) => {
      console.log(data);
      window.location.href = '/client-db';
      this.newUser.passwordConfirm = '';
      alert('Changes saved!');
    });
  }
}
