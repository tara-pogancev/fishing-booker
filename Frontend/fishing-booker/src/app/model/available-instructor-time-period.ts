import { AvailableTimePeriod } from "./available-time-period";

export class AvailableInstructorTimePeriod{
    constructor(
        public instructorId:number=-1,
        public startDate: number,
        public endDate: number,
        public id: number
      ) {
          
      }
}