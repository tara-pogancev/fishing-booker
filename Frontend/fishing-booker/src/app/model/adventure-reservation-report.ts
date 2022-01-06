export class AdventureReservationReport{
    constructor(
        public reportId:number,
        public adventureName:string,
        public clientName:string,
        public ownerName:string,
        public comment:string,
        public reportType:string
    ){
    }
}