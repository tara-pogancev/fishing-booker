import { Component, OnInit } from '@angular/core';
import { FishingInstructorService } from 'src/app/service/fishing-instructor.service';

@Component({
  selector: 'app-fishin-instructors-list',
  templateUrl: './fishin-instructors-list.component.html',
  styleUrls: ['./fishin-instructors-list.component.css']
})
export class FishinInstructorsListComponent implements OnInit {

  instructors:any=[];
  constructor(private instructorService:FishingInstructorService) { }

  ngOnInit(): void {
    this.instructorService.loadInstructors().subscribe((loadedInstructors:any)=>this.instructors=loadedInstructors);
  }

  deleteInstructor(instructor:any){
    const index = this.instructors.indexOf(instructor);
    this.instructors.splice(index, 1);
  }

}
