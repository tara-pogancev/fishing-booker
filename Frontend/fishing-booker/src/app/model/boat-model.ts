import { Utility } from './utility-model';

export class Boat {
  constructor(
    public id: number = 0,
    public name: string = '',
    public boatType: string = '',
    public boatLength: number = 0,
    public rating: number = 0,
    public price: number = 0,
    public numberOfEngines: number = 0,
    public enginePower: number = 0,
    public maxSpeed: number = 0,
    public navigationalEquipments: string[] = [],
    public address: string = '',
    public description: string = '',
    public guestLimit: number = 0,
    public rules: string[] = [],
    public fishingEquipment: string = '',
    public utilities: Utility[] = [],
    public ownerName: string = '',
    public imageIds: number[] = []
  ) {}
}
