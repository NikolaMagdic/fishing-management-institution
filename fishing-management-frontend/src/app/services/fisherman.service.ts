import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class FishermanService {
    
    private url = "http://localhost:8080/api/fisherman";

    constructor(private http: HttpClient) { }

    getFishermansWithLicenseRequest() {
        return this.http.get(this.url + "/license-requests");
    }
}