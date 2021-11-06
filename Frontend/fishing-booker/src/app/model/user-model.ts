export class UserModel {
  constructor(
    public name: string = '',
    public lastName: string = '',
    public email: string = '',
    public password: string = '',
    public passwordConfirm: string = '',
    public phone: string = '',
    public role: string = 'Registered Client',
    public street: string = '',
    public country: string = '',
    public city: string = ''
  ) {}
}