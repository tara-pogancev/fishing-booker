import { Component, ChangeDetectorRef } from '@angular/core';
import {
  CalendarEvent,
  CalendarMonthViewBeforeRenderEvent,
  CalendarWeekViewBeforeRenderEvent,
  CalendarDayViewBeforeRenderEvent,
  CalendarView,
} from 'angular-calendar';
import { setDate } from 'date-fns';
import { start } from 'repl';

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
      start: new Date('11/30/2021'),
      end: new Date('12/15/2021'),
    },
    {
      title: 'Available',
      start: new Date('11/05/2021'),
      end: new Date('11/20/2021'),
    },
  ];

  constructor(private cdr: ChangeDetectorRef) {}

  beforeViewRender(event: CalendarMonthViewBeforeRenderEvent) {
    this.cdr.detectChanges();
  }
}
