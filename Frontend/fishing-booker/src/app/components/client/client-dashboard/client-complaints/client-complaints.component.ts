import { Component, Input, OnInit } from '@angular/core';
import { ComplaintCardComponent } from 'src/app/components/complaint-card/complaint-card.component';
import { Client } from 'src/app/model/client-model';
import { ComplaintModel } from 'src/app/model/complaint-model';
import { ReservationModel } from 'src/app/model/reservation-model';
import { ComplaintService } from 'src/app/service/complaint.service';

@Component({
  selector: 'client-complaints',
  templateUrl: './client-complaints.component.html',
  styleUrls: ['./client-complaints.component.css'],
})
export class ClientComplaintsComponent implements OnInit {
  @Input() user: Client = new Client();

  reservations: ReservationModel[] = [];
  visibleMessage: boolean = false;

  complaint: ComplaintModel = new ComplaintModel();

  constructor(private complaintService: ComplaintService) {}

  ngOnInit(): void {
    this.complaintService.getClientAvailableComplaints().subscribe((data) => {
      this.reservations = data;
    });
  }

  submit() {
    if (this.complaint.id == 0) {
      alert('Please select a reservation from the dropdown list!');
    } else if (this.complaint.description == '') {
      alert('Please enter your message!');
    } else {
      this.complaintService.newComplaint(this.complaint).subscribe((data) => {
        this.visibleMessage = true;
      });
    }
  }

  hideMessage() {
    this.visibleMessage = false;
  }
}
