import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FishStockingService {

  private url = "http://localhost:8080/api/fish-population-modification";

  constructor(private http: HttpClient) { }

  getAllFishStockings() {
    return this.http.get(this.url);
  }

  makeFishStocking(fishStocking: any) {
    return this.http.post(this.url, fishStocking); 
  }

}
