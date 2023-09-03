import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { License } from "../models/license";

@Injectable({
    providedIn: 'root'
})
export class LicenseService {

    private url = "http://localhost:8080/api/license";

    constructor(private http: HttpClient) {}

    getExistingValidLicenses() {
        return this.http.get(this.url);
    }

    obtainLicence(license: License) {
        return this.http.post(this.url, license);
    }

    confirmLicenseRequest(fishermanId: number) {
        return this.http.patch(this.url + "/" + fishermanId, null);
    }
}