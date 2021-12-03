import { Component, OnInit } from '@angular/core';
import { FishingInstructorService } from 'src/app/service/fishing-instructor.service';

@Component({
  selector: 'app-fishing-instructor-personal-info',
  templateUrl: './fishing-instructor-personal-info.component.html',
  styleUrls: ['./fishing-instructor-personal-info.component.css']
})
export class FishingInstructorPersonalInfoComponent implements OnInit {

  instructor:any;
  fullAddress:string='';
  password="";
  passwordConfirm="";

  constructor(private instructorService: FishingInstructorService) { }

  ngOnInit(): void {
    this.instructorService.getCurrentInstructor().subscribe((data: any) => {
      this.instructor = data;
      this.fullAddress=this.instructor.userAddress.city+' '+this.instructor.userAddress.street;
      console.log(data);
    });
    
  }
}
