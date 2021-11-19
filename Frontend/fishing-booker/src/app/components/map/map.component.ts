import { Component, Input, OnInit } from '@angular/core';
import { Loader } from '@googlemaps/js-api-loader';
import { MapService } from 'src/app/service/map.service';
import { config } from 'src/config';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css'],
})
export class MapComponent implements OnInit {
  @Input() address: string = 'Bahami';
  lat: number = 0;
  lng: number = 0;
  constructor(private mapService: MapService) {}

  ngOnInit(): void {
    this.mapService.getCoordinates(this.address).subscribe((data) => {
      console.log("Address: " + this.address);

      this.lat = data.results[0].geometry.location.lat;
      this.lng = data.results[0].geometry.location.lng;

      let loader = new Loader({
        apiKey: config.API_TOKEN,
      });

      loader.load().then(() => {
        const map = new google.maps.Map(document.getElementById('map')!, {
          center: { lat: this.lat, lng: this.lng },
          zoom: 14,
        });

        new google.maps.Marker({
          position: { lat: this.lat, lng: this.lng },
          map,
        });
      });
    });
  }
}
