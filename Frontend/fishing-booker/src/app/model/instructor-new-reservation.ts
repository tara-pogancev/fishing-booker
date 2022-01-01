import { Utility } from "./utility-model";

export class InstructorNewReservation{
    constructor(public adventureId:number=-1,
        public clientId:number=-1,
        public startDate:number=-1,
        public endDate:number=-1,
        public utilities:Utility[]=[],
        public price:number=0,
        public guestNumber:number=0){

    }
}