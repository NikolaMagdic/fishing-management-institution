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

    confirmCatch(itemId: number) {
        return this.http.patch(this.url + "/confirm/" + itemId, null);
    }

    rejectCatch(itemId: number) {
        return this.http.patch(this.url + "/reject/" + itemId, null);
    }

    getYearlyCatchesOfFisherman(fishermanId: number, year: number) {
        // Primer dodavanja Query parametara u GET zahtev
        let queryParams = new HttpParams().append("year", year);
        return this.http.get(this.url + "/fisherman/" + fishermanId + "/year", {params: queryParams});
    }
}