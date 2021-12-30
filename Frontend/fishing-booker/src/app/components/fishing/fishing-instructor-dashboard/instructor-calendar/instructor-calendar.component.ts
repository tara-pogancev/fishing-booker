
import {
  Component,
  ChangeDetectionStrategy,
  ViewChild,
  TemplateRef,
  OnInit,
} from '@angular/core';
import {
  startOfDay,
  endOfDay,
  subDays,
  addDays,
  endOfMonth,
  isSameDay,
  isSameMonth,
  addHours,
} from 'date-fns';
import { Subject } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {
  CalendarEvent,
  CalendarEventAction,
  CalendarEventTimesChangedEvent,
  CalendarView,
} from 'angular-calendar';
import { MyCalendarEvent } from '../../../../interfaces/calendar-event';
import { start } from 'repl';
import { AvailableTimePeriod } from '../../../../model/available-time-period';
import { FishingInstructorService } from '../../../../service/fishing-instructor.service';
import { AvailableInstructorTimePeriod } from '../../../../model/available-instructor-time-period';
import { ReservationModel } from '../../../../model/reservation-model';
import { InstructorCalendarResevation } from '../../../../model/instructor-calendar-reservation';
import { ChangeTimeSlot } from '../../../../model/change-time-slot';

const colors: any = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3',
  },
  green: {
    primary: '#00FF00',
    secondary: '#32CD32',
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA',
  },
};

@Component({
  selector: 'app-instructor-calendar',
  templateUrl: './instructor-calendar.component.html',
  styleUrls: ['./instructor-calendar.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  styles: [
    `
      h3 {
        margin: 0 0 10px;
      }

      pre {
        background-color: #f5f5f5;
        padding: 15px;
      }
    `,
  ],
  
})
export class InstructorCalendarComponent implements OnInit{
  @ViewChild('modalContent', { static: true }) modalContent: TemplateRef<any>;

  view: CalendarView = CalendarView.Month;

  availablePeriods:AvailableInstructorTimePeriod[]=[]; 

  reservations:InstructorCalendarResevation[]=[];

  CalendarView = CalendarView;

  viewDate: Date = new Date();

  endDate:Date=new Date();

  startDate:Date=new Date();

  currentFreeSlots:MyCalendarEvent[]=[]; //slotovi koji se mogu mijenjati. Mogu se mijenjati samo oni koji jos uvijek traju

  

  modalData: {
    action: string;
    event: CalendarEvent;
  };


  ngOnInit(): void {
    
    this.getAvailableTimes();
    this.fishingInstructorService.getInstructorReservations().subscribe(data=>{
      this.reservations=data;
      this.reservations.forEach(reservation=>this.createReservationEvent(reservation));
      this.refresh.next();
    })
    
  }

  getAvailableTimes(){
    this.availablePeriods.length=0;
    this.currentFreeSlots.length=0;
    this.fishingInstructorService.getAvailableTimes().subscribe(data=> {
      this.availablePeriods=data;
      this.availablePeriods.forEach(period=> this.createAvailbleTimeEvent(period));
      this.refresh.next();
    });
  }
  
;

  

  actions: CalendarEventAction[] = [
    {
      label: '<i class="fas fa-fw fa-pencil-alt"></i>',
      a11yLabel: 'Edit',
      onClick: ({ event }: { event: CalendarEvent }): void => {
        this.handleEvent('Edited', event);
      },
    },
    {
      label: '<i class="fas fa-fw fa-trash-alt"></i>',
      a11yLabel: 'Delete',
      onClick: ({ event }: { event: CalendarEvent }): void => {
        this.events = this.events.filter((iEvent) => iEvent !== event);
        this.currentFreeSlots = this.currentFreeSlots.filter((iEvent) => iEvent !== event);
        this.handleEvent('Deleted', event);
      },
    },
  ];

  refresh = new Subject<void>();

  createAvailbleTimeEvent(period:AvailableInstructorTimePeriod){
    var event:MyCalendarEvent={
      id:period.id,
      start:(new Date(period.startDate)),
      end:(new Date(period.endDate)),
      title:'Available',
      color:colors.green,
      draggable:true,
      endDateString:(new Date(period.endDate)).toISOString().slice(0, 16),
      startDateString:(new Date(period.startDate)).toISOString().slice(0,16),
      reservationType:'Available',
    };
    this.events.push(event);
    let k=new Date(event.endDateString);
    let p=new Date();
    if (k>p){
      this.currentFreeSlots.push(event);
    }
    

  }

  createReservationEvent(reservation: InstructorCalendarResevation): void {

    var event:MyCalendarEvent={
      id:reservation.reservationId,
      start:(new Date(reservation.startDate)),
      end:(new Date(reservation.endDate)),
      title:'Quick Reservation',
      color:colors.yellow,
      draggable:true,
      endDateString:(new Date(reservation.endDate)).toISOString().slice(0, 16),
      startDateString:(new Date(reservation.startDate)).toISOString().slice(0,16),
      reservationType:'Quick Reservation',
    };
    if (reservation.reservationType=="QuickReservation"){
      event.title='Quick Reservation';
      event.reservationType='Quick Reservation';
      event.color=colors.yellow;
    }else{
      event.title='Reservation';
      event.reservationType='Reservation';
      event.color=colors.red;
    }
    
    this.events.push(event);
  }

  events: MyCalendarEvent[] = [];

  activeDayIsOpen: boolean = true;

  constructor(private modal: NgbModal,private fishingInstructorService:FishingInstructorService) {}

  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
      }
      this.viewDate = date;
    }
  }

  handleEvent(action: string, event: CalendarEvent): void {
    this.modalData = { event, action };
    this.modal.open(this.modalContent, { size: 'lg' });
  }

  addEvent(): void {

    var element=document.getElementById("addNewSlot");
    if (element!=null){
      element.classList.toggle("addNewTime");
    }
  }

  deleteEvent(eventToDelete: MyCalendarEvent) {
    this.fishingInstructorService.deleteAvailableTimePeriod(eventToDelete.id).subscribe(data=>{
      if (data==true){
        this.events = this.events.filter((event) => event !== eventToDelete);
        this.currentFreeSlots=this.currentFreeSlots.filter((event) => event !== eventToDelete);
        this.refresh.next();
      }else{
        alert('Selected free time slot contains reservations!');
      }
    })
    

  }

  setView(view: CalendarView) {
    this.view = view;
  }

  closeOpenMonthViewDay() {
    this.activeDayIsOpen = false;
  }

  onChangeDate(event:MyCalendarEvent){
    const index=this.events.indexOf(event);
    var p=this.events[index].end;
        if (p==undefined){
          p=new Date();
        }
    var timePeriod:ChangeTimeSlot=new ChangeTimeSlot(-1,event.id,new Date(event.start).getTime()/1000,new Date(p).getTime()/1000,new Date(event.startDateString).getTime()/1000,new Date(event.endDateString).getTime()/1000);
    this.fishingInstructorService.updateFreeSlot(timePeriod).subscribe(data=>{
      if (data==true){
        this.events[index].end=(new Date(event.endDateString));
        this.events[index].start=(new Date(event.startDateString));
      }else{
        alert('Instructor available time overlaps with already defined available time or does not contain already defined reservations');
        this.events[index].startDateString=this.events[index].start.toISOString().slice(0,16);
        var k=this.events[index].end;
        if (k==undefined){
          k=new Date();
        }
        this.events[index].endDateString=k.toISOString().slice(0,16);
      }
    });
    
    
    this.refresh.next();
  }

  confirmAdding(){
    var timePeriod:AvailableInstructorTimePeriod=new AvailableInstructorTimePeriod(-1,new Date(this.startDate).getTime()/1000,new Date(this.endDate).getTime()/1000,-1);
    this.fishingInstructorService.addAvailableTime(timePeriod);
    this.getAvailableTimes();
    this.refresh.next(); 
  }
}


