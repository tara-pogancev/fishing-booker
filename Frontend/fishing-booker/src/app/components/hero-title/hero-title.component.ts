import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'hero-title',
  templateUrl: './hero-title.component.html',
  styleUrls: ['./hero-title.component.css'],
})
export class HeroTitleComponent implements OnInit {
  @Input() title: string = 'Title';
  @Input() subtitle: string = 'Subtitle';
  @Input() image: string = '';

  constructor() {}

  ngOnInit(): void {}
}
