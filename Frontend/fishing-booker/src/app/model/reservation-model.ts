export class ReservationModel {
  constructor(
    public userId: number = 0,
    public entityType: string = '',
    public entityName: string = '',
    public ownerName: string = '',
    public entityId: number = 0,
    public startDate: Date = new Date(),
    public endDate: Date = new Date(),
    public price: number = 0,
    public people: number = 0,
    public utilityIds: number[] = [],
    public id: number = 0,
    public canCancel: boolean = true,
    public createdReport = false
  ) {}
}
