import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FishSpecies } from '../models/fish-species';

@Injectable({
  providedIn: 'root'
})
export class FishSpeciesService {

  private url = "http://localhost:8080/api/fish-species";

  constructor(private http: HttpClient) { }

  getFishSpecies() {
    return this.http.get(this.url);
  }

  createFishSpecies(fish: FishSpecies) {
    return this.http.post(this.url, fish);
  }
}
