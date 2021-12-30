export class CalendarReservationInfo{
    constructor(
        public  reservationId:number,
        public  clientName:string,
        public  clientSurname:string,
        public  start:Date,
        public  end:Date,
        public  price:number,
        public  guestNumber:number
    ){}
}