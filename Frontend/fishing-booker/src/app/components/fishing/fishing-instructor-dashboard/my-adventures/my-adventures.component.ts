import { Component, OnInit } from '@angular/core';
import { Adventure } from 'src/app/model/adventure-model';
import { SearchFilter } from 'src/app/model/search-filter-model';
import { AdvetnureService } from 'src/app/service/adventure-service';
import { ImageService } from 'src/app/service/image.service';
import { SearchService } from 'src/app/service/search-service';

@Component({
  selector: 'app-my-adventures',
  templateUrl: './my-adventures.component.html',
  styleUrls: ['./my-adventures.component.css']
})
export class MyAdventuresComponent implements OnInit {

  adventures: Adventure[] = [];
  adventuresAll: Adventure[] = [];
  text:string='';

  image: any;

  constructor(
    private adventureService: AdvetnureService,
    private searchService: SearchService,
    private imageService:ImageService
  ) {}

  ngOnInit(): void {
    this.adventureService.getInstructorAdventures().subscribe((data) => {
      this.adventures = data;
      this.adventuresAll = data;
    });

    

  }

  getImage(adventure:any){
    if (adventure.imageUrls.length != 0) {
      this.image=adventure.imageUrls[0];
      return this.image;
    } else {
      return 'assets/images/placeholder.jpg';
    }
  }
  

  search() {
    let searchFilter=new SearchFilter();
    searchFilter.text=this.text;
    this.adventures = this.searchService.filterAdventures(this.adventuresAll,searchFilter)!;
  }

  createImageFromBlob(image: Blob) {
    let reader = new FileReader();
    reader.addEventListener(
      'load',
      () => {
        this.image = reader.result;
      },
      false
    );

    if (image) {
      reader.readAsDataURL(image);
    }
  }

  getImageFromService(id: number) {
    this.imageService.getImage(id).subscribe((data) => {
      this.createImageFromBlob(data);
    });
  }

  deleteAdventure(adventure:Adventure){
    let canDelete=true;
    this.adventureService.deleteAdventure(adventure.id).subscribe(
      data=>{
       canDelete=data
       if (canDelete==true){
        const index=this.adventures.indexOf(adventure);
        this.adventures.splice(index,1);
      }else{
        alert('You can not delete reserved adventure');
      }
      });
    
    
  }

}
