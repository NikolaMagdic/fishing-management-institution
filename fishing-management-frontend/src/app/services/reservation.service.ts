import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Reservation } from "../models/reservation";

@Injectable({
    providedIn: 'root'
})
export class ReservationService {

    private url = "http://localhost:8080/api/reservation";

    constructor(private http: HttpClient) {}

    createReservation(reservation: Reservation) {
        return this.http.post(this.url, reservation);
    }

    getOccupiedDatesForFishingSpot(spotId: number, areaId: number) {
        return this.http.get(this.url + "/spot/" + spotId + "/area/" + areaId + "/future");
    } 

    getAllReservationsOfFisherman(fishermanId: number) {
        return this.http.get(this.url + "/fisherman/" + fishermanId);
    }

    getAllReservationsForFishingSpot(spotId: number, areaId: number) {
        return this.http.get(this.url + "/spot/" + spotId + "/area/" + areaId);
    }

    cancelReservation(reservationId: number) {
        return this.http.delete(this.url + "/" + reservationId);
    }
}