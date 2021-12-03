import { Component, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client-model';
import { FishingInstructorService } from 'src/app/service/fishing-instructor.service';

@Component({
  selector: 'app-instructor-settings',
  templateUrl: './instructor-settings.component.html',
  styleUrls: ['./instructor-settings.component.css']
})
export class InstructorSettingsComponent implements OnInit {

  instructor: any = {};
  validPassword: boolean = true;
  validForm: boolean = true;
  city: string='';
  street:string='';
  newInstructor:Client=new Client();
  passwordConfirm='';
  country='';
  requestSent=false;
  requestText='';
  constructor(private instructorService:FishingInstructorService) { }

  ngOnInit(): void {
    this.instructorService.getCurrentInstructor().subscribe((data: any) => {
      this.instructor = data;
      this.instructor.password = '';
      this.instructor.passwordConfirm = '';
    });
  }

  validatePassword() {
    this.validPassword = this.instructor.password == this.passwordConfirm;
  }

  validateForm() {
    this.validForm =
      this.instructor.name != '' &&
      this.instructor.lastName != '' &&
      this.instructor.phone != '' &&
      this.instructor.street != '' &&
      this.instructor.city != '' &&
      this.instructor.country != '' &&
      this.instructor.password != '' &&
      this.passwordConfirm != '' &&
      this.validPassword;

    if (this.validForm == true) {
      this.updateInstructor();
    }

  }

  updateInstructor() {
    
    
    this.instructorService.updateInstructor(this.instructor).subscribe((data) => {
      console.log(data);
      window.location.href = '/instructor-db';
      this.passwordConfirm = '';
      alert('Changes saved!');
    });
  }

  sendRequest(){
    if (this.requestText==''){
      alert('You must enter message');
      return;
    }
    this.requestSent=true;
    this.requestText='';
  }

}
