export class Reservation {
    public id: number;
    public arrivalDate: Date;
    public fishingSpotId: number;

    constructor($id: number, $arrivalDate: Date, $fishingSpotId: number) {
        this.id = $id;
        this.arrivalDate = $arrivalDate;
        this.fishingSpotId = $fishingSpotId;
    }
}