import { Component, OnInit } from '@angular/core';
import { FishingInstructorService } from 'src/app/service/fishing-instructor.service';

@Component({
  selector: 'app-fishing-instructor-dashboard',
  templateUrl: './fishing-instructor-dashboard.component.html',
  styleUrls: ['./fishing-instructor-dashboard.component.css']
})
export class FishingInstructorDashboardComponent implements OnInit {

  instructor: any = {};
  activeTab: string = 'PERSONAL_INFO';
  newAdmin:any={};
  passwordConfirm='';

  constructor(private instructorService: FishingInstructorService) {}

  ngOnInit(): void {
   this.instructorService.getCurrentInstructor().subscribe((data: any) => {
      this.instructor = data;
      console.log(data);
    });
  }

  changeTab(tabName: string) {
    this.activeTab = tabName;
  }


}
