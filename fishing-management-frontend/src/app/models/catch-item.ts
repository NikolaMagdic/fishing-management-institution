import { RouterTestingHarness } from "@angular/router/testing";

export class CatchItem {
    public fishId: number;
    public quantity: number;
    public weight: number;

    constructor($fishId: number, $quantity: number, $weight: number) {
        this.fishId = $fishId;
        this.quantity = $quantity;
        this.weight = $weight;
    }
}