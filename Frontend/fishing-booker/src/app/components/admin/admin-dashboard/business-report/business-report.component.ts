import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../../../service/admin.service';
import { SystemPropertiesService } from 'src/app/service/system-properties.service';

@Component({
  selector: 'app-business-report',
  templateUrl: './business-report.component.html',
  styleUrls: ['./business-report.component.css']
})
export class BusinessReportComponent implements OnInit {

  startDate=new Date();
  endDate=new Date();
  reservations:any=[]
  price=0;
  profit=0;
  commisionPrecentage=0.1;
  constructor(private adminService:AdminService,private systemPropertiesService:SystemPropertiesService) { }

  ngOnInit(): void {
    this.adminService.getBusinessReport().subscribe(data=>{
      this.reservations=data;
      this.calculatePrice();
    })
    this.systemPropertiesService.getPercentage().subscribe(percentage=>this.commisionPrecentage=percentage);
  }

  calculatePrice(){
    this.price=0;
    this.reservations.forEach((element:any) => {
      this.price+=element.price;
      element.profit=Math.round((element.price*this.commisionPrecentage)) / 100;
      this.profit+=element.profit;
    });
  }

  search(){
    console.log(this.startDate);
    console.log(this.endDate);
    this.adminService.getBusinessReportInDate(new Date(this.startDate),new Date(this.endDate)).subscribe(data=>{
      this.reservations.length=0;
      this.reservations=data;
      this.calculatePrice();
    });
  }

}
