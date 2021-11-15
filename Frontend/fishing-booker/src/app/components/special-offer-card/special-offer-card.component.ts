import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'special-offer-card',
  templateUrl: './special-offer-card.component.html',
  styleUrls: ['./special-offer-card.component.css'],
})
export class SpecialOfferCardComponent implements OnInit {
  description: string = '';
  constructor() {}

  ngOnInit(): void {
    this.description =
      'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin in ex quis eros placerat maximus non id nunc. Mauris id dolor ut tortor scelerisque laoreet. Pellentesque rutrum mauris at iaculis commodo. Nulla aliquam laoreet libero, vitae dictum leo viverra in. Proin condimentum est a ante feugiat, aliquet malesuada lectus commodo. Aliquam porttitor lectus vitae tempor molestie. Nunc ac sollicitudin leo, id interdum nibh. Pellentesque fringilla mauris quam, non ullamcorper mauris dapibus ut. Praesent efficitur odio at urna viverra tincidunt. Proin aliquam, augue non pretium vulputate, ligula dui posuere nibh, sed ullamcorper nulla nisl sit amet dolor. In at sapien augue. Aenean turpis ante, ultrices ut laoreet et, elementum nec velit.';
  }
}
