export class DeleteAccountRequest{
    constructor(
        public name: string = '',
        public lastName: string = '',
        public mail: string = '',
        public userType: string = '',
        public description: string = '',
        public id: string = ''
      ) {}
}