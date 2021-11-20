import { Component, OnInit, Input } from '@angular/core';
import { Cottage } from 'src/app/model/cottage-model';
import { CottageService } from 'src/app/service/cottage.service';

@Component({
  selector: 'cottage-card',
  templateUrl: './cottage-card.component.html',
  styleUrls: ['./cottage-card.component.css'],
})
export class CottageCardComponent implements OnInit {
  @Input() id: number = 0;
  @Input() cottage: Cottage = new Cottage();
  @Input() img: string = '/assets/images/cottage-interior/interior1.jpeg';

  constructor(private cottageService: CottageService) {}

  ngOnInit(): void {
    this.cottageService.findById(this.id).subscribe((data) => {
      this.cottage = data;
    });
  }

  readMore() {
    window.location.href = 'cottage/' + this.id;
  }
}
