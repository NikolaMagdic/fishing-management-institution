import { Injectable } from "@angular/core";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Observable, catchError, throwError, tap } from "rxjs";
import { FishingArea } from "../models/fishing-area";

@Injectable()
export class FishingAreaService {

    private url = "http://localhost:8080/api/fishing-area";

    constructor(private http: HttpClient) {}

    getFishingAreas() {
        // Slanje http zahteva
        return this.http.get(this.url)
                // Rad sa primljenim podacima i obrada greske - hvatanje exceptiona
                .pipe(
                    tap(data => console.log("All: ", JSON.stringify(data))),
                    catchError(this.handleError)
                );
    
    }

    // Gresku mozemo obraditi na nacin na koji zelimo
    private handleError(err: HttpErrorResponse) {
        console.log(err.message);
        return throwError(() => err.message);
    }

    getFishingAreaById(id: number) {
        return this.http.get(this.url + "/" + id);
    }

    createFishingArea(fishingArea: FishingArea) {
        return this.http.post(this.url, fishingArea);   
    }

    addFishSpeciesToArea(areaId: number, fishId: number) {
        return this.http.patch(this.url + "/" + areaId + "/add-fish-species/" + fishId, null);
    }

    getFishingAreasNotManagedByKeeper(keeperId: number) {
        return this.http.get(this.url + "/not-keeper/" + keeperId);
    }

    getFishingAreasManagedByKeeper(keeperId: number) {
        return this.http.get(this.url + "/keeper/" + keeperId)
    }
}