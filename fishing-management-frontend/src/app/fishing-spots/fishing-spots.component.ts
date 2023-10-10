import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FishingSpot } from '../models/fishing-spot';
import { FishingSpotService } from '../services/fishing-spot.service';
import Map from 'ol/Map.js';
import OSM from 'ol/source/OSM.js';
import TileLayer from 'ol/layer/Tile';
import View from 'ol/View.js';
import { fromLonLat, Projection, transform } from 'ol/proj';
import { Feature } from 'ol';
import Style from 'ol/style/Style';
import Icon from 'ol/style/Icon';
import { Point } from 'ol/geom';
import VectorLayer from 'ol/layer/Vector';
import VectorSource from 'ol/source/Vector';
import { toStringHDMS } from 'ol/coordinate';
import { ImageService } from '../services/image.service';

@Component({
  selector: 'app-fishing-spots',
  templateUrl: './fishing-spots.component.html',
  styleUrls: ['./fishing-spots.component.css']
})
export class FishingSpotsComponent {

  fishingSpots: any = [];
  newFishingSpot: FishingSpot = new FishingSpot(0, "", 0, 0, "", 0);
  map: Map | undefined;
  image: any;

  addFishingSpotButtonVisible = false;

  constructor(private fishingSpotService: FishingSpotService,
              private imageService: ImageService,
              private route: ActivatedRoute,
              private router: Router) {}

  ngOnInit(): void {
    let id = Number(this.route.snapshot.paramMap.get('id'));
    this.fishingSpotService.getFishingSpots(id).subscribe({
      next: data => {
        this.fishingSpots = data;
      }
    });
    this.newFishingSpot.fishingAreaId = id;

    // Mapa
    this.map = new Map({
      target: 'map',
      layers: [
        new TileLayer({
          source: new OSM(),
        }),
      ],
      view: new View({
        center: fromLonLat([19.82, 45.04]),
        zoom: 10,
      }),
    });

    this.map.on('singleclick', (event) => {
      console.log(event.coordinate);
      let coords = transform(event.coordinate, 'EPSG:3857', 'EPSG:4326');
      this.newFishingSpot.latitude = coords[1];
      this.newFishingSpot.longitude = coords[0];
      let marker = new Feature({
        geometry: new Point(fromLonLat([this.newFishingSpot.longitude, this.newFishingSpot.latitude]))
      });
      markers.getSource()?.clear();
      markers.getSource()?.addFeature(marker);

    });

    var markers = new VectorLayer({
      source: new VectorSource(),
      style: new Style({
        image: new Icon({
          anchor: [0.5, 0.73],
          anchorXUnits: 'fraction',
          anchorYUnits: 'fraction',
          src: '../../assets/marker.png'
        })
      })
    });

    this.map.addLayer(markers);

    const role = localStorage.getItem('role');
    if(role == 'ROLE_ADMIN') {
      this.addFishingSpotButtonVisible = true;
    }
  }

  createFishingSpot() {

    if(this.image) {
      this.imageService.uploadImage(this.image).subscribe({
        next: imagePath => {
          this.newFishingSpot.image = imagePath as string;
          this.fishingSpotService.createSpot(this.newFishingSpot).subscribe({}); 
          window.location.reload();
        }
      });
    } else {
      this.fishingSpotService.createSpot(this.newFishingSpot).subscribe({}); 
      window.location.reload();
    }
  }

  viewFishingSpotDetails(spotId: number) {
    this.router.navigate(['/fishing-spot-details/' + spotId]);
  }

  processImage(imageFile: any) {
    const file: File = imageFile.files[0];
    this.image = file;
  }

}
