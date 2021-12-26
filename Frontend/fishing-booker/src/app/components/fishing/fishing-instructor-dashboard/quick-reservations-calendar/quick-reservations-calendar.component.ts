import { Component, Input, OnInit } from '@angular/core';
import { CalendarEvent, CalendarView } from 'angular-calendar';
import { AvailableTimePeriod } from '../../../../model/available-time-period';

@Component({
  selector: 'app-quick-reservations-calendar',
  templateUrl: './quick-reservations-calendar.component.html',
  styleUrls: ['./quick-reservations-calendar.component.css']
})
export class QuickReservationsCalendarComponent implements OnInit {

  view: CalendarView = CalendarView.Month;
  viewDate: Date = new Date();
  events: CalendarEvent[] = [];
  @Input() quickReservations: any = [];

  constructor() {}

  ngOnInit(): void {
    this.events = [];
    console.log(this.quickReservations);
    for (let date of this.quickReservations) {
      let event = {
        title: 'Adventure Quick Action',
        start: new Date(date.startDate),
        end: new Date(date.endDate),
      };

      this.events.push(event);
    }
  }

}
