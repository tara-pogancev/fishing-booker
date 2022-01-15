import { Component, OnInit, Input } from '@angular/core';
import { CottageOwnerService } from 'src/app/service/cottage-owner.service';
import { DeleteAccountService } from 'src/app/service/delete-account.service';

@Component({
  selector: 'app-cottage-owner-settings',
  templateUrl: './cottage-owner-settings.component.html',
  styleUrls: ['./cottage-owner-settings.component.css']
})
export class CottageOwnerSettingsComponent implements OnInit {
  @Input() cottageOwner: any;
  newCottageOwner: any;
  validPassword: boolean = true;
  validForm: boolean = true;

  requestSent=false;
  requestText='';

  constructor(private cottageOwnerService: CottageOwnerService, private deleteAccountService: DeleteAccountService) { }

  ngOnInit(): void {
    this.cottageOwnerService.getCurrentCottageOwner().subscribe((data: any) => {
      this.newCottageOwner = data;
      this.newCottageOwner.password = '';
      this.newCottageOwner.passwordConfirm = '';
    });
  }

  validatePassword() {
    this.validPassword = this.newCottageOwner.password == this.newCottageOwner.passwordConfirm;
  }

  validateForm() {
    this.validForm =
      this.newCottageOwner.name != '' &&
      this.newCottageOwner.lastName != '' &&
      this.newCottageOwner.phone != '' &&
      this.newCottageOwner.street != '' &&
      this.newCottageOwner.city != '' &&
      this.newCottageOwner.country != '' &&
      this.newCottageOwner.password != '' &&
      this.newCottageOwner.passwordConfirm != '' &&
      this.validPassword;

    if (this.validForm == true) {
      this.updateClient();
    }
  }

  updateClient() {
    this.cottageOwnerService.updateCottageOwnerData(this.newCottageOwner).subscribe((data) => {
      console.log(data);
      window.location.href = '/cottage-owner-db';
      this.newCottageOwner.passwordConfirm = '';
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
