import { Utility } from "./utility-model";

export class NewReservation{
    constructor(public entityId:number=-1,
        public clientId:number=-1,
        public startDate:number=-1,
        public endDate:number=-1,
        public utilities:Utility[]=[],
        public price:number=0,
        public guestNumber:number=0){

    }
}