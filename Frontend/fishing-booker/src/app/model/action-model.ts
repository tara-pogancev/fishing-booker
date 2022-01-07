import { Utility } from './utility-model';

export class ActionModel {
  constructor(
    public id: number = 0,
    public entityId: number = 0,
    public entityType: string = '',
    public entityName: string = '',
    public address: string = '',
    public description: string = '',
    public guestLimit: number = 0,
    public utilities: Utility[] = [],
    public ownerName: string = '',
    public imageUrls: string[] = [],
    public price: number = 0,
    public rating: number = 0,
    public startDate: Date = new Date(),
    public endDate: Date = new Date()
  ) {}
}
