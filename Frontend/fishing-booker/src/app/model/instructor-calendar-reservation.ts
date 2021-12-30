
export class InstructorCalendarResevation{
    constructor(
        public reservationId:number=-1,
        public reservationType: string,
        public startDate: Date,
        public endDate: Date
      ) {
          
      }
}