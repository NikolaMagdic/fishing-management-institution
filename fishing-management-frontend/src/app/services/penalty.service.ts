import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class PenaltyService {
    
    private url = "http://localhost:8080/api/penalty";

    constructor(private http: HttpClient) {}

    getPenaltyById(id: number) {
        return this.http.get(this.url + "/" + id);
    }

    getAllPenalties() {
        return this.http.get(this.url);
    }

    getAllPenalitesOfFisherman(fishermanId: number) {
        return this.http.get(this.url + "/fisherman/" + fishermanId);
    }

    addPenalty(penalty: any) {
        return this.http.post(this.url, penalty);
    }

    updatePenalty(penaltyId: number, penalty: any) {
        return this.http.put(this.url + "/" + penaltyId, penalty);
    }

    imposeAPenalty(penaltyId: number, fishermanId: number) {
        return this.http.patch(this.url + "/" + penaltyId + "/impose/" + fishermanId, null);
    }
}