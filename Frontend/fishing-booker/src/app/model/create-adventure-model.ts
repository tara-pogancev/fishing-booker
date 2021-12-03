import { Image } from "./image-model";
import { Utility } from "./utility-model";

export class CreateAdventureModel {
    constructor(
      public name: string = '',
      public description: string = '',
      public street:string='',
      public city:string='',
      public country:string='',
      public guestLimit: number = 0,
      public price: number = 0,
      public cancelationFee=0,
      public rules: string[] = [],
      public additionalServices: Utility[] = [],
      public fishingEquipment:string[]=[],
      public ownerId: string = '',
      public images: Image[] = []  //url encoded images with name and extension
    ) {}
  }