export class RegistrationRequest {
  constructor(
    public name: string = '',
    public lastName: string = '',
    public mail: string = '',
    public userType: string = '',
    public registrationDescription: string = '',
    public id: string = ''
  ) {}
}
