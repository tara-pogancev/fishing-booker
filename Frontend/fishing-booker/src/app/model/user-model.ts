export class UserModel {
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
    public city: string = ''
  ) {}
}

export class AuthRequest {
  constructor(public email: string = '', public password: string = '') {}
}

export class ActiveUser {
  asObservable() {
    throw new Error('Method not implemented.');
  }
  constructor(
    public id: number = -1,
    public name: string = '',
    public jwt: string = '',
    public role: string = 'LOGGED_OUT',
    public email: string = ''
  ) {}
}
