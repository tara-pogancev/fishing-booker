import { DatePipe } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { AvailableBoatTimePeriod } from 'src/app/model/available-boat-time-period';
import { Boat } from 'src/app/model/boat-model';
import { AvailableBoatTimePeriodService } from 'src/app/service/available-boat-time-period.service';
import { BoatService } from 'src/app/service/boat.service';

@Component({
  selector: 'app-boat-owner-available-periods',
  templateUrl: './boat-owner-available-periods.component.html',
  styleUrls: ['./boat-owner-available-periods.component.css']
})
export class BoatOwnerAvailablePeriodsComponent implements OnInit {
  @Input() boatOwner: any;
  boats: Boat[] = [];
  selectedBoatId: number = -1;
  selectedBoat: Boat = new Boat;
  startDate: any = null;
  endDate: any = null;
  availablePeriods: AvailableBoatTimePeriod[] = [];
  allAvailablePeriods: AvailableBoatTimePeriod[] = [];
  pipe = new DatePipe('en-US');

  constructor(private boatService: BoatService, private availableBoatTimePeriodService: AvailableBoatTimePeriodService) { }

  ngOnInit(): void {
    this.boatService.findByBoatOwnerId(this.boatOwner.id).subscribe(
      (data) => {
        this.boats = data;
      }
    );
    this.availableBoatTimePeriodService.findAll().subscribe(
      (data) => {
        //this.availablePeriods = data;
        this.allAvailablePeriods = data;
      }
    );
  }

  createAvailablePeriod() {
    if (this.selectedBoatId == -1 || this.startDate == null || this.endDate == null) {
      alert('Please fill in all fields.')
      return;
    }
    
    let startDate = new Date(this.startDate);
    let endDate = new Date(this.endDate);
    if (startDate > endDate) {
      alert('End date must be greater then start date.')
      return;
    }
    let availableBoatTimePeriod: AvailableBoatTimePeriod = new AvailableBoatTimePeriod(this.selectedBoatId, startDate, endDate);
    this.availableBoatTimePeriodService.addAvailableBoatTimePeriod(availableBoatTimePeriod).subscribe();
    this.allAvailablePeriods.push(availableBoatTimePeriod);
    this.availablePeriods.push(availableBoatTimePeriod);
    alert('Available period successfuly added');
  }

  onChange(event: any) {
    let selectedBoat = this.boats.find(x => x.id == this.selectedBoatId);
    if (selectedBoat == undefined) {
      this.selectedBoat = new Boat;
    }
    else {
      this.selectedBoat = selectedBoat;
    }

    this.availablePeriods = this.allAvailablePeriods.filter(x => x.boatId == this.selectedBoatId);
    console.log(this.allAvailablePeriods);
    console.log(this.allAvailablePeriods.filter(x => x.boatId == this.selectedBoatId));
  }

}
