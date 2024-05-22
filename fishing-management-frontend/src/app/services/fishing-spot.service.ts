import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FishingSpot } from '../models/fishing-spot';

@Injectable({
  providedIn: 'root'
})
export class FishingSpotService {

  private url = "http://localhost:8080/api/fishing-spot";
  
  constructor(private http: HttpClient) { }

  getFishingSpots(areaId: number) {
    return this.http.get(this.url + "/all/" + areaId);
  }

  getFishingSpotById(spotId: number, areaId: number) {
    return this.http.get(this.url + "/" + spotId + "/area/" + areaId);
  }

  createSpot(fishingSpot: FishingSpot) {
    return this.http.post(this.url, fishingSpot);
  }

  updateFishingSpot(spotId: number, fishingSpot: FishingSpot) {
    return this.http.put(this.url + "/" + spotId, fishingSpot);
  }

}
