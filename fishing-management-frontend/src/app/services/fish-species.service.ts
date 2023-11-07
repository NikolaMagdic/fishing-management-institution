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

  getFishSpeciesById(id: number) {
    return this.http.get(this.url + "/" + id);
  }

  createFishSpecies(fish: FishSpecies) {
    return this.http.post(this.url, fish);
  }

  updateFishSpecies(fish: FishSpecies) {
    return this.http.put(this.url + "/" + fish.id, fish);
  }

  getFishSpeciesOutsideArea(id: number) {
    return this.http.get(this.url + "/outside-area/" + id);
  }

  getFishSpeciesInArea(id: number) {
    return this.http.get(this.url + "/area/" + id);
  }

  getNativeFishSpecies() {
    return this.http.get(this.url + "/native");
  }
}
