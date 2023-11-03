export class Reservation {
    public id: number;
    public arrivalDate: Date;
    public departureDate: Date | undefined;
    public fishingSpotId: number;
    public fishingAreaId: number;
    public licenseId!: number; 

    constructor($id: number, $arrivalDate: Date, $fishingSpotId: number, $fishingAreaId: number) {
        this.id = $id;
        this.arrivalDate = $arrivalDate;
        this.fishingSpotId = $fishingSpotId;
        this.fishingAreaId = $fishingAreaId;
    }
}