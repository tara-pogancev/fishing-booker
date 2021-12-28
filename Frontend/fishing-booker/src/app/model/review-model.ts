export class ReviewModel {
  constructor(
    public review: string = '',
    public id: number = 0,
    public rating: number = 5,
    public date: Date = new Date(),
    public approval: string = '',
    public reservationName: string = '',
    public reservationStart: Date = new Date(),
    public reservationEnd: Date = new Date(),
    public clientName: string = '',
    public reservationType: string = ''
  ) {}
}
