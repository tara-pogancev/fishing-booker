import { Utility } from "./utility-model";

export class AdventureQuickReservation{
    constructor(
    public adventureId:number=0,
    public adventureUtilityDtoList:Utility[]=[],
    public guestLimit:number=0,
    public actionStart:Date=new Date(),
    public actionEnd:Date=new Date(),
    public price:number=0,
    ){}
}