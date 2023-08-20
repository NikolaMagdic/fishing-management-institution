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
import { ReservationService } from '../services/reservation.service';
import { Reservation } from '../models/reservation';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-fishing-spot-details',
  templateUrl: './fishing-spot-details.component.html',
  styleUrls: ['./fishing-spot-details.component.css']
})
export class FishingSpotDetailsComponent {

  fishingSpot: FishingSpot | any;
  map: Map | undefined;

  futureReservations: Reservation[] = [];
  datesReserved: Date[] = [];
  minDate = new Date();
  // Primer upotrebe FormControl za forme (Reactive forms u Angularu)
  date = new FormControl();

  constructor(private fishingSpotService: FishingSpotService,
              private reservationService: ReservationService, 
              private route: ActivatedRoute) { }

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

    this.reservationService.getOccupiedDatesForFishingSpot(id).subscribe({
      next: data => {
        var dates = data as Date[];
        // Moram pretvoriti u Date jer datepicker ne prepoznaje LocalDate
        dates.forEach(date => {
          let d = new Date(date);
          console.log(date);
          this.datesReserved.push(d);
        });
      }
    })
  }

  createReservation() {
    var reservation = new Reservation(0, this.date.value, this.fishingSpot.id);
    this.reservationService.createReservation(reservation).subscribe({});
  }
}
