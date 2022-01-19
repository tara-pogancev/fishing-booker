import { Rule } from "./rule-model";
import { Utility } from "./utility-model";
import { Image } from "./image-model";

export class CreateBoatModel {
    constructor(
        public id: number = -1,
        public name: string = '',
        public boatType: string = '',
        public boatLength: number = 0,
        public price: number = 0,
        public numberOfEngines: number = 0,
        public enginePower: number = 0,
        public maxSpeed: number = 0,
        public navigationalEquipments: string[] = [],
        public street:string='',
        public city:string='',
        public country:string='',
        public description: string = '',
        public guestLimit: number = 0,
        public rules: Rule[] = [],
        public fishingEquipment: string = '',
        public additionalServices: Utility[] = [],
        public ownerId: number = -1,
        public images: Image[] = [],
        public cancellationPercentageKeep: number = 0) {}
}