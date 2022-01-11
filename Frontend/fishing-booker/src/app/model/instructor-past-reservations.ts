export class InstructorPastReservation{
    constructor(
        public reservationId:number=-1,
        public reservationType:string='',
        public adventureId:number=-1,
        public adventureName:string='',
        public startDate:Date=new Date(),
        public endDate:Date=new Date(),
        public numberOfPeople:number=-1,
        public price:number=-1,
        public clientId:number=-1,
        public clientName:string='',
        public clientLastName:string='',
        public clientMail:string='',
        public clientfullAddress:string='',
        public clientPhoneNumber:string='',
        public createdReport:boolean=false
    ){

    }
}