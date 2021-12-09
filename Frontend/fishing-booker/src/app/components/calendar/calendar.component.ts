import { Component } from '@angular/core';
import {
  CalendarEvent,
  CalendarView,
} from 'angular-calendar';

@Component({
  selector: 'entity-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css'],
})
export class CalendarComponent {
  view: CalendarView = CalendarView.Month;
  viewDate: Date = new Date();

  events: CalendarEvent[] = [
    {
      title: 'Available',
      start: new Date('12/15/2021'),
      end: new Date('12/16/2021'),
    },
    {
      title: 'Available',
      start: new Date('12/18/2021'),
      end: new Date('12/21/2021'),
    },    
    
  ];

  constructor() {}

}
