import { CalendarEvent } from "angular-calendar";

export interface MyCalendarEvent extends CalendarEvent{
    id:number;
    endDateString: string;
    startDateString: string;
    reservationType:string,
}