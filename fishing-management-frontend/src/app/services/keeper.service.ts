import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Keeper } from "../models/keeper";
import { RegistrationRequest } from "../models/registration-request";

@Injectable({
    providedIn: 'root'
})
export class KeeperService {

    private url = "http://localhost:8080/api/keeper";

    constructor(private http: HttpClient) { }

    getKeepers() {
        return this.http.get(this.url);
    }

    getKeeperById(id: number) {
        return this.http.get(this.url + '/' + id);
    }

    registerKeeper(keeper: RegistrationRequest) {
        return this.http.post(this.url, keeper);
    }

    updateKeeper(keeper: Keeper) {
        return this.http.put(this.url + "/" + keeper.id, keeper);
    }

    addAreaToManage(keeperId: number, areaId: number) {
        return this.http.patch(this.url + "/" + keeperId +  "/add-fishing-area/" + areaId, null);
    }

    removeManagedArea(keeperId: number, areaId: number) {
        return this.http.patch(this.url + "/" + keeperId + "/remove-fishing-area/" + areaId, null);
    }
}