export class FishingSpot {
    public id: number;
    public type: string;
    public latitude: number;
    public longitude: number;
    public fishingAreaId: number;

    constructor($id: number, $type: string, $latitude: number, $longitude: number, $fishingAreaId: number) {
        this.id = $id;
        this.type = $type;
        this.latitude = $latitude;
        this.longitude = $longitude;
        this.fishingAreaId = $fishingAreaId;
    }
}