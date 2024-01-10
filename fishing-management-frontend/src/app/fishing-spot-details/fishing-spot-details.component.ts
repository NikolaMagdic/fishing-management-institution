import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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
import { FormControl, FormGroup } from '@angular/forms';
import { LicenseService } from '../services/license.service';
import { License } from '../models/license';
import { FishingAreaService } from '../services/fishing-area.service';
import { FishingArea } from '../models/fishing-area';
import { FishermanService } from '../services/fisherman.service';
import { initTimestamp } from 'ngx-bootstrap/chronos/units/timestamp';

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
  date: any;
  dateRange: any;
  // Primer upotrebe FormControl za forme (Reactive forms u Angularu)
  // date = new FormControl();
  errorMessageShown = false;
  multiDayReservationForm = false;

  @ViewChild('successModal') successModal: ElementRef | any;
  @ViewChild('failureModal') failureModal: ElementRef | any;
  @ViewChild('failureMultiModal') failureMultiModal: ElementRef | any;

  validLicense: boolean = false;
  fishermanLoggedIn = false;
  licenseId: any;
  fishingAreaName = '';
  professionalFishermanLoggedIn = false;

  constructor(private fishingSpotService: FishingSpotService,
              private reservationService: ReservationService,
              private licenseService: LicenseService, 
              private fishingAreaService: FishingAreaService,
              private fishermanService: FishermanService,
              private route: ActivatedRoute,
              private router: Router) {
               }

  ngOnInit() {
    let areaId = Number(this.route.snapshot.paramMap.get('areaId'));
    let spotId = Number(this.route.snapshot.paramMap.get('spotId'));

    const role = localStorage.getItem('role');
    if(role == "ROLE_FISHERMAN") {
      this.getFishermanCategory();
      this.fishermanLoggedIn = true;
      this.getExistingValidLicenses();
    }

    this.getFishingAreaName(areaId);

    this.fishingSpotService.getFishingSpotById(spotId, areaId).subscribe({
      next: data => {
        this.fishingSpot = data;
        console.log(this.fishingSpot);
  
        // Iz nekog razloga nece da se prikaze mapa unutar bootstrap kontejnera bez timeouta
        setTimeout(()=> {this.initMap();}, 200);
      }
      
    });
    
    this.reservationService.getOccupiedDatesForFishingSpot(spotId, areaId).subscribe({
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

  initMap() {
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

  getExistingValidLicenses() {
    this.licenseService.getExistingValidLicenses().subscribe({
      next: data => {
        if(data != null) {
          this.validLicense = true;  
        } 
      }
    });
  }

  createReservation() {
    if(this.validLicense) {
      let resDate = new Date(Date.UTC(this.date.getFullYear(), this.date.getMonth(), this.date.getDate()));
      var reservation = new Reservation(0, resDate, this.fishingSpot.id, this.fishingSpot.fishingAreaId);
      if(this.licenseId) {
        reservation.licenseId = this.licenseId;
      }
      this.reservationService.createReservation(reservation).subscribe({
        next: () => {
          this.successModal.nativeElement.click();
        }
      });
    } else {
      this.failureModal.nativeElement.click();
    }
  }

  createMultiDayReservation() {
    if(this.validLicense) {
      let startDate = this.dateRange[0];
      let endDate = this.dateRange[1];
      let resStartDate = new Date(Date.UTC(startDate.getFullYear(), startDate.getMonth(), startDate.getDate()));
      let resEndDate = new Date(Date.UTC(endDate.getFullYear(), endDate.getMonth(), endDate.getDate()));
      console.log("OVDE SAM");
      console.log(resEndDate);
      var reservation = new Reservation(0, resStartDate, this.fishingSpot.id, this.fishingSpot.fishingAreaId);
      reservation.departureDate = resEndDate;
      if(this.licenseId) {
        reservation.licenseId = this.licenseId;
      }
      this.reservationService.createReservation(reservation).subscribe({
        next: () => {
          this.successModal.nativeElement.click();
        },
        error: () => {
          this.errorMessageShown = true;
        }
      });
    } else {
      this.failureMultiModal.nativeElement.click();
    }
  }

  reloadPage() {
    window.location.reload();
  }

  obtainDailyLicense() {
    const license: License = new License(0, "DAILY", this.date, new Date(), 0);
    this.licenseService.obtainLicence(license).subscribe({
      next: data => {
        this.validLicense = true;
        let l = data as License;
        this.licenseId = l.id;
        this.createReservation();
      }
    })
  }

  obtainMultiDayLicense() {
    const license: License = new License(0, "MULTIDAY", this.dateRange[0], this.dateRange[1], 0);
    this.licenseService.obtainLicence(license).subscribe({
      next: data => {
        this.validLicense = true;
        let l = data as License;
        this.licenseId = l.id;
        this.createMultiDayReservation();
      }
    });
  }

  showReservations() {
    let areaId = Number(this.route.snapshot.paramMap.get('areaId'));
    let spotId = Number(this.route.snapshot.paramMap.get('spotId'));
    this.router.navigate(["/reservations/" + areaId + '/' + spotId]);
  }

  onDateChange(event: any) {
    this.date = event;
  }

  onDateRangeChange(event: any) {
    this.dateRange = event;
    console.log(this.dateRange);
  }

  getFishingAreaName(areaId: number) {
    this.fishingAreaService.getFishingAreaById(areaId).subscribe({
      next: data => {
        let fishingArea = data as FishingArea;
        this.fishingAreaName = fishingArea.name;
      }
    });
  }

  getFishermanCategory() {
    this.fishermanService.getCategoryOfLoggedFisherman().subscribe({
      next: data => {
        let category: string = data as string;
        if(category === "Profesionalni") {
          this.professionalFishermanLoggedIn = true;
        }
      }
    });
  }

}
