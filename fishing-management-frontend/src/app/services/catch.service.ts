import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Catch } from "../models/catch";

@Injectable({
    providedIn: 'root'
})
export class CatchService {

    private url = "http://localhost:8080/api/catch";

    constructor(private http: HttpClient) {    }

    getAllCatchesOfFisherman(fishermanId: number) {
        return this.http.get(this.url + "/fisherman/" + fishermanId);
    }

    createCatch(c: Catch) {
        return this.http.post(this.url, c);
    }

    confirmCatch(catchId: number, itemId: number) {
        return this.http.patch(this.url + "/confirm/" + catchId + "/" + itemId, null);
    }

    rejectCatch(catchId: number, itemId: number) {
        return this.http.patch(this.url + "/reject/" + catchId + "/" + itemId, null);
    }

    getAllCatchesOfFishermanForYear(fishermanId: number, year: number) {
        let queryParams = new HttpParams().append("year", year);
        return this.http.get(this.url + "/fisherman/" + fishermanId + "/for-year", {params: queryParams});
    }

    getYearlyCatchesOfFisherman(fishermanId: number, year: number) {
        // Primer dodavanja Query parametara u GET zahtev
        let queryParams = new HttpParams().append("year", year);
        return this.http.get(this.url + "/fisherman/" + fishermanId + "/year", {params: queryParams});
    }

    getYearlyCatchesInFishingArea(areaId: number, year: number) {
        let queryParams = new HttpParams().append("year", year);
        return this.http.get(this.url + "/fishing-area/" + areaId + "/year", {params: queryParams});
    }

    getYearlyCatchesOfNobleFishSpeciesInFishingArea(areaId: number, year: number) {
        let queryParams = new HttpParams().append("year", year);
        return this.http.get(this.url + "/noble/fishing-area/" + areaId + "/year", {params: queryParams});
    }

    updateCatchItem(catchItem: any, catchId: number, itemId: number) {
        return this.http.put(this.url + "/" + catchId + "/" + itemId, catchItem);
    }
}