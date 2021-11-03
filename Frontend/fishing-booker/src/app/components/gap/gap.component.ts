import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'gap',
  templateUrl: './gap.component.html',
  styleUrls: ['./gap.component.css'],
})
export class GapComponent implements OnInit {
  @Input() height: string = '50';

  constructor() {}

  ngOnInit(): void {}
}
