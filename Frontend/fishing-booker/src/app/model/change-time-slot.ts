export class ChangeTimeSlot{
    constructor(
        public instructorId:number,
        public id:number,
        public startDate:number,
        public endDate:number,
        public newStartDate:number,
        public newEndDate:number
    ){

    }
}