import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FishingSpot } from '../models/fishing-spot';
import { FishingSpotService } from '../services/fishing-spot.service';
import { Feature, Map, View } from 'ol';
import VectorLayer from 'ol/layer/Vector';
import TileLayer from 'ol/layer/Tile';
import OSM from 'ol/source/OSM';
import VectorSource from 'ol/source/Vector';
import Style from 'ol/style/Style';
import Icon from 'ol/style/Icon';
import { fromLonLat } from 'ol/proj';
import { Point } from 'ol/geom';

@Component({
  selector: 'app-fishing-spot-details',
  templateUrl: './fishing-spot-details.component.html',
  styleUrls: ['./fishing-spot-details.component.css']
})
export class FishingSpotDetailsComponent {

  fishingSpot: FishingSpot | any;
  map: Map | undefined;

  constructor(private fishingSpotService: FishingSpotService, 
              private route: ActivatedRoute) {}

  ngOnInit() {
    let id = Number(this.route.snapshot.paramMap.get('id'));
    this.fishingSpotService.getFishingSpotById(id).subscribe({
      next: data => {
        this.fishingSpot = data;
        console.log(this.fishingSpot);
  
        this.map = new Map({
          target: 'map',
          layers: [
            new TileLayer({
              source: new OSM(),
            }),
            new VectorLayer({
              source: new VectorSource({
                features: [
                  new Feature({
                    geometry: new Point(fromLonLat([this.fishingSpot.longitude, this.fishingSpot.latitude]))
                  })
                ]
              }),
              style: new Style({
                image: new Icon({
                  anchor: [0.5, 0.73],
                  anchorXUnits: 'fraction',
                  anchorYUnits: 'fraction',
                  src: '../../assets/marker.png'
                })
              })
            })
          ],
          view: new View({
            center: fromLonLat([this.fishingSpot.longitude, this.fishingSpot.latitude]),
            zoom: 15
          })
        });

        
      }
    });

  }
}
