export class ComplaintModel {
  constructor(
    public id: number = 0,
    public entityId: number = 0,
    public date: Date = new Date(),
    public description: string = '',
    public approval: string = '',
    public reservationType: string = '',
    public reservationName: string = '',
    public reservationStart: Date = new Date(),
    public reservationEnd: Date = new Date(),
    public userName: string = '',
    public userId: number = 0
  ) {}
}
