import { Component, OnInit } from '@angular/core';
import { CottageService } from 'src/app/service/cottage.service';

@Component({
  selector: 'app-cottage-list',
  templateUrl: './cottage-list.component.html',
  styleUrls: ['./cottage-list.component.css']
})
export class CottageListComponent implements OnInit {

  cottages:any=[];
  constructor(private cottageService:CottageService) { }

  ngOnInit(): void {
    this.cottageService.getCottages().subscribe((cottages:any)=>this.cottages=cottages);
  }

  deleteCottage(cottage:any){
      this.cottageService.deleteCottage(cottage.id).subscribe();
      const index=this.cottages.indexOf(cottage);
      this.cottages.splice(index,1);
  }

}
