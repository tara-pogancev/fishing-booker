import { Component, Input, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';
import { DeleteAccountRequest } from 'src/app/model/delete-request-model';
import { DeleteAccountService } from 'src/app/service/delete-account.service';

@Component({
  selector: 'client-delete-account',
  templateUrl: './client-delete-account.component.html',
  styleUrls: ['./client-delete-account.component.css'],
})
export class ClientDeleteAccountComponent implements OnInit {
  @Input() user: Client = new Client();
  request: DeleteAccountRequest = new DeleteAccountRequest();

  visibleMessage: boolean = false;
  visibleMessageBox: boolean = false;

  constructor(private deleteAccountService: DeleteAccountService) {}

  ngOnInit(): void {}

  submit() {
    if (this.request.description == '') {
      alert('Please type in your message before submitting!');
    } else {
      this.deleteAccountService.sendRequest(this.request.description);
      this.visibleMessage = true;
      this.visibleMessageBox = true;
    }
  }

  hideMessage() {
    this.visibleMessageBox = false;
  }
}
