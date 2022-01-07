import {
  Component,
  Input,
  OnInit,
  ChangeDetectionStrategy,
} from '@angular/core';
import {
  CalendarEvent,
  CalendarDateFormatter,
  CalendarView,
} from 'angular-calendar';
import { AvailableTimePeriod } from 'src/app/model/available-time-period';
import { CustomDateFormatter } from './custom-date-formatter.provider';

@Component({
  selector: 'entity-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css'],
  providers: [
    {
      provide: CalendarDateFormatter,
      useClass: CustomDateFormatter,
    },
  ],
})
export class CalendarComponent implements OnInit {
  view: CalendarView = CalendarView.Month;
  viewDate: Date = new Date();
  events: CalendarEvent[] = [];
  @Input() availableDates: AvailableTimePeriod[] = [];

  constructor() {}

  ngOnInit(): void {
    this.events = [];
    for (let date of this.availableDates) {
      let event = {
        title: 'Available',
        start: new Date(date.startDate),
        end: new Date(date.endDate),
      };

      this.events.push(event);
    }
  }
}
