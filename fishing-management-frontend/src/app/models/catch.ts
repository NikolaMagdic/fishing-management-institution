import { CatchItem } from "./catch-item";

export class Catch {
    public fishingAreaId: number;
    public catchItems: CatchItem[];
    public date: Date | any;

    constructor($fishingAreaId: number, $catchItems: CatchItem[], $date: Date | any) {
        this.fishingAreaId = $fishingAreaId;
        this.catchItems = $catchItems;
        this.date = $date;
    }
}