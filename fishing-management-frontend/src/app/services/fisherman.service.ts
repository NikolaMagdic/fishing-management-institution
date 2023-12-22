import { HttpClient, HttpParams } from "@angular/common/http";
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

    getFishermansPageable(page: number) {
        let queryParams = new HttpParams().append("page", page)
        return this.http.get(this.url + "/page", {params: queryParams});
    }

    getFishermansWithLicenseRequest() {
        return this.http.get(this.url + "/license-requests");
    }

    getFishermansWithNonConfirmedCatches(page: number) {
        let queryParams = new HttpParams().append("page", page);
        return this.http.get(this.url + "/not-confirmed-catches", {params: queryParams});
    }

    updateFisherman(fisherman: Fisherman) {
        return this.http.put(this.url + "/" + fisherman.id, fisherman);
    }

    searchFishermansByFirstNameAndLastName(page: number, firstName: string, lastName: string, category: number) {
        let queryParams = new HttpParams().append("page", page)
                                    .append("firstName", firstName)
                                    .append("lastName", lastName)
                                    .append("category", category)
        return this.http.get(this.url + "/search", {params: queryParams});   
    }

    getCategoryOfLoggedFisherman() {
        return this.http.get(this.url + "/category");
    } 
}