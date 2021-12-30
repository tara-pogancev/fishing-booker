import { CalendarEvent } from "angular-calendar";

export interface ReservationEvent extends CalendarEvent{
    reservationId:number;
    clientId:number;
    reservationType:string;

}