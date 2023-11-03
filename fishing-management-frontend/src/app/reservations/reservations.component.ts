import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ReservationService } from '../services/reservation.service';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent {
  
  reservations: any = [];
  fishermanLoggedIn = false;
  
  constructor(
    private reservationService: ReservationService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    // Ubacio sam malo polimorfizma - ako ovu komponentu pozove ribolovac prikazace se jedna tabela
    // a za ribocuvara i admina druga 
    const role = localStorage.getItem('role');
    if(role == 'ROLE_FISHERMAN') {
      this.getAllReservationsOfFisherman();
      this.fishermanLoggedIn = true;
    } else {
      // Admin ili ribocuvar su prijavljeni
      this.getAllReservationsForFishingSpot();
    }    
  }

  getAllReservationsOfFisherman() {
    let fishermanId = Number(this.route.snapshot.paramMap.get('id'));
    this.reservationService.getAllReservationsOfFisherman(fishermanId).subscribe({
      next: data => {
        this.reservations = data;
      }
    });
  }

  getAllReservationsForFishingSpot() {
    let areaId = Number(this.route.snapshot.paramMap.get('areaId'));
    let spotId = Number(this.route.snapshot.paramMap.get('spotId'));
    this.reservationService.getAllReservationsForFishingSpot(areaId, spotId).subscribe({
      next: data => {
        this.reservations = data;
      }
    });
  }
}
