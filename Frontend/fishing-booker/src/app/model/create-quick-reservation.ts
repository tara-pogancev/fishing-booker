import { Utility } from "./utility-model";

export class CreateAdventureQuickReservation{
    constructor(
    public adventureId:number=0,
    public adventureUtilityDtoList:Utility[]=[],
    public guestLimit:number=0,
    public actionStart:number=0,
    public actionEnd:number=0,
    public price:number=0,
    ){}
}