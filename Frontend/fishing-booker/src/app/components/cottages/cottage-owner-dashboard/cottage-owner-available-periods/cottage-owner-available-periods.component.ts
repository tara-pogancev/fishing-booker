import { DatePipe } from '@angular/common';
import { Component, OnInit, Input } from '@angular/core';
import { AvailableCottageTimePeriod } from 'src/app/model/available-cottage-time-period';
import { Cottage } from 'src/app/model/cottage-model';
import { AvailableCottageTimePeriodService } from 'src/app/service/available-cottage-time-period.service';
import { CottageService } from 'src/app/service/cottage.service';

@Component({
  selector: 'app-cottage-owner-available-periods',
  templateUrl: './cottage-owner-available-periods.component.html',
  styleUrls: ['./cottage-owner-available-periods.component.css']
})
export class CottageOwnerAvailablePeriodsComponent implements OnInit {
  @Input() cottageOwner: any;
  cottages: Cottage[] = [];
  selectedCottageId: number = -1;
  selectedCottage: Cottage = new Cottage;
  startDate: any = null;
  endDate: any = null;
  availablePeriods: AvailableCottageTimePeriod[] = [];
  allAvailablePeriods: AvailableCottageTimePeriod[] = [];
  pipe = new DatePipe('en-US');

  constructor(private cottageService: CottageService, private availableCottageTimePeriodService: AvailableCottageTimePeriodService) { }

  ngOnInit(): void {
    this.cottageService.findByCottageOwnerId(this.cottageOwner.id).subscribe(
      (data) => {
        this.cottages = data;
      }
    );
    this.availableCottageTimePeriodService.findAll().subscribe(
      (data) => {
        //this.availablePeriods = data;
        this.allAvailablePeriods = data;
      }
    );
  }

  createAvailablePeriod() {
    if (this.selectedCottageId == -1 || this.startDate == null || this.endDate == null) {
      alert('Please fill in all fields.')
      return;
    }
    
    let startDate = new Date(this.startDate);
    let endDate = new Date(this.endDate);
    if (startDate > endDate) {
      alert('End date must be greater then start date.')
      return;
    }
    let availableCottageTimePeriod: AvailableCottageTimePeriod = new AvailableCottageTimePeriod(this.selectedCottageId, startDate, endDate);
    this.availableCottageTimePeriodService.addAvailableCottageTimePeriod(availableCottageTimePeriod).subscribe();
    this.allAvailablePeriods.push(availableCottageTimePeriod);
    this.availablePeriods.push(availableCottageTimePeriod);
    alert('Available period successfuly added');
  }

  onChange(event: any) {
    let selectedCottage = this.cottages.find(x => x.id == this.selectedCottageId);
    if (selectedCottage == undefined) {
      this.selectedCottage = new Cottage;
    }
    else {
      this.selectedCottage = selectedCottage;
    }

    this.availablePeriods = this.allAvailablePeriods.filter(x => x.cottageId == this.selectedCottageId);
    console.log(this.allAvailablePeriods);
    console.log(this.allAvailablePeriods.filter(x => x.cottageId == this.selectedCottageId));
  }
}
