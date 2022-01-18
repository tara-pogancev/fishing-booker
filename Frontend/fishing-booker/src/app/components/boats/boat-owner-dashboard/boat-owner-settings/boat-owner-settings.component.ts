import { Component, Input, OnInit } from '@angular/core';
import { BoatOwnerService } from 'src/app/service/boat-owner.service';
import { DeleteAccountService } from 'src/app/service/delete-account.service';

@Component({
  selector: 'app-boat-owner-settings',
  templateUrl: './boat-owner-settings.component.html',
  styleUrls: ['./boat-owner-settings.component.css']
})
export class BoatOwnerSettingsComponent implements OnInit {
  @Input() boatOwner: any;
  newBoatOwner: any;
  validPassword: boolean = true;
  validForm: boolean = true;

  requestSent=false;
  requestText='';

  constructor(private boatOwnerService: BoatOwnerService, private deleteAccountService: DeleteAccountService) { }

  ngOnInit(): void {
    this.boatOwnerService.getCurrentBoatOwner().subscribe((data: any) => {
      this.newBoatOwner = data;
      this.newBoatOwner.password = '';
      this.newBoatOwner.passwordConfirm = '';
    });
  }

  validatePassword() {
    this.validPassword = this.newBoatOwner.password == this.newBoatOwner.passwordConfirm;
  }

  validateForm() {
    this.validForm =
      this.newBoatOwner.name != '' &&
      this.newBoatOwner.lastName != '' &&
      this.newBoatOwner.phone != '' &&
      this.newBoatOwner.street != '' &&
      this.newBoatOwner.city != '' &&
      this.newBoatOwner.country != '' &&
      this.newBoatOwner.password != '' &&
      this.newBoatOwner.passwordConfirm != '' &&
      this.validPassword;

    if (this.validForm == true) {
      this.updateClient();
    }
  }

  updateClient() {
    this.boatOwnerService.updateBoatOwnerData(this.newBoatOwner).subscribe((data) => {
      console.log(data);
      window.location.href = '/boat-owner-db';
      this.newBoatOwner.passwordConfirm = '';
      alert('Changes saved!');
    });
  }

  sendRequest() {
    if (this.requestText==''){
      alert('You must enter message');
      return;
    }
    this.deleteAccountService.sendRequest(this.requestText);
    this.requestSent=true;
    this.requestText='';
  }

}
