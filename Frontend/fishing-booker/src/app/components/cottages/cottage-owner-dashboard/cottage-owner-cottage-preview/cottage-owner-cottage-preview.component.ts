import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Cottage } from 'src/app/model/cottage-model';
import { CottageService } from 'src/app/service/cottage.service';
import { ImageService } from 'src/app/service/image.service';

@Component({
  selector: 'app-cottage-owner-cottage-preview',
  templateUrl: './cottage-owner-cottage-preview.component.html',
  styleUrls: ['./cottage-owner-cottage-preview.component.css']
})
export class CottageOwnerCottagePreviewComponent implements OnInit {
  id: number = 0;
  cottage: Cottage = new Cottage();
  image: any = 'assets/images/placeholder.jpg';

  constructor(
    private route: ActivatedRoute,
    private cottageService: CottageService,
    private imageService: ImageService
  ) { }

  ngOnInit(): void {
    this.id = +this.route.snapshot.paramMap.get('id')!;

    this.cottageService.findById(this.id).subscribe((data) => {
      this.cottage = data;
    });
  }

  editCottage() {
    location.href="edit-cottage/"+this.id;
  }

}
