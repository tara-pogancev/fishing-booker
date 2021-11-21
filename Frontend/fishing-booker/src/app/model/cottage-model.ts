import { Utility } from './utility-model';

export class Cottage {
  constructor(
    public id: number = 0,
    public rating: number = 0,
    public price: number = 0,
    public name: string = '',
    public address: string = '',
    public description: string = '',
    public guestLimit: number = 0,
    public rules: string[] = [],
    public utilities: Utility[] = [],
    public rooms: Room[] = [],
    public ownerName: string = '',
    public roomOverview: string = '',    
    public imageIds: number[] = []
  ) {}
}

export class Room {
  constructor(public id: number = 0, public numberOfBeds: number = 0) {}
}
