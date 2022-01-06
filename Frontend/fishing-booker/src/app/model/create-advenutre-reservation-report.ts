export class CreateAdventureReservationReport{
    constructor(
        public reservationId:number=-1,
        public comment:string='',
        public badComment:boolean=false,
        public noClient:boolean=false
    ){

    }
}