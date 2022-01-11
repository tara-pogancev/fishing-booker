import { Component, OnInit } from '@angular/core';
import { SystemPropertiesService } from 'src/app/service/system-properties.service';

@Component({
  selector: 'app-commision-precentage',
  templateUrl: './commision-precentage.component.html',
  styleUrls: ['./commision-precentage.component.css']
})
export class CommisionPrecentageComponent implements OnInit {


  startDate=new Date();
  endDate=new Date();
  commissionPercentage:Number=0;
  constructor(private systemPropertiesService:SystemPropertiesService) { }

  ngOnInit(): void {
    this.systemPropertiesService.getPercentage().subscribe(percentage=>this.commissionPercentage=percentage);
  }

  setPercentage(){
    
    this.systemPropertiesService.setPercentage(this.commissionPercentage).subscribe(
      data=>alert('Successfully changed'),
      error =>console.log('Error')
    );
  }

  search(){
    
  }

}
