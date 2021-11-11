export class Client {
  constructor(
    public name: string = '',
    public lastName: string = '',
    public email: string = '',
    public password: string = '',
    public passwordConfirm: string = '',
    public phone: string = '',
    public role: string = 'Registered Client',
    public fullAddress: string = '',
    public street: string = '',
    public country: string = '',
    public city: string = '',
    public penalties: number = 0,
    public isBlocked: boolean = false
  ) {}
}
