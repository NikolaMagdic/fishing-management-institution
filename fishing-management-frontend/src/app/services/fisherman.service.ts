import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Fisherman } from "../models/fisherman";

@Injectable({
    providedIn: 'root'
})
export class FishermanService {
    
    private url = "http://localhost:8080/api/fisherman";

    constructor(private http: HttpClient) { }

    getFishermanById(id: number) {
        return this.http.get(this.url + "/" + id);
    }

    getAllFishermans() {
        return this.http.get(this.url);
    }

    getFishermansWithLicenseRequest() {
        return this.http.get(this.url + "/license-requests");
    }

    getFishermansWithNonConfirmedCatches() {
        return this.http.get(this.url + "/not-confirmed-catches");
    }

    updateFisherman(fisherman: Fisherman) {
        return this.http.put(this.url + "/" + fisherman.id, fisherman);
    }
}