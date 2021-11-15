import { Component, OnInit } from '@angular/core';
import { Loader } from '@googlemaps/js-api-loader';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css'],
})
export class MapComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {
    let loader = new Loader({
      apiKey: 'AIzaSyCX5DQFPxHlQlEeFkkWzTJ41PU6FehGzVs',
    });

    loader.load().then(() => {
      new google.maps.Map(document.getElementById('map')!, {
        center: { lat: 45.232593070379785, lng: 19.842512847955057 },
        zoom: 14,
      });
    });
  }
}
