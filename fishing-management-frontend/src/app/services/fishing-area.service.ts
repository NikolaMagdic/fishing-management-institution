import { Injectable } from "@angular/core";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Observable, catchError, throwError, tap } from "rxjs";

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

                // {
            //     "id": 1,
            //     "name": "Borkovac",
            //     "type": "Jezero",
            //     "image": "https://www.gradnja.rs/wp-content/uploads/2022/04/borkovac-ruma-naslovna.jpg"
            // },
            // {
            //     "id": 2,
            //     "name": "Sava",
            //     "type": "Reka",
            //     "image": "https://turizamusrbiji.rs/wp-content/uploads/2014/10/belgrade-sava-river.jpg"
            // }
}