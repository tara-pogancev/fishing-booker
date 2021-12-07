import { FishingEquipment } from "./fishing-equipment-model";
import { Image } from "./image-model";
import { Rule } from "./rule-model";
import { Utility } from "./utility-model";

export class CreateAdventureModel {
    constructor(
      public id:Number=-1,
      public name: string = '',
      public description: string = '',
      public street:string='',
      public city:string='',
      public country:string='',
      public guestLimit: number = 0,
      public price: number = 0,
      public cancellationFee=0,
      public rules: Rule[] = [],
      public additionalServices: Utility[] = [],
      public fishingEquipment:FishingEquipment[]=[],
      public ownerId: number = -1,
      public images: Image[] = []  //url encoded images with name and extension
    ) {}
  }