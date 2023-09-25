export class FishingSpot {
    public id: number;
    public type: string;
    public latitude: number;
    public longitude: number;
    public image: string;
    public fishingAreaId: number;

    constructor($id: number, $type: string, $latitude: number, $longitude: number, $image: string, $fishingAreaId: number) {
        this.id = $id;
        this.type = $type;
        this.latitude = $latitude;
        this.longitude = $longitude;
        this.image = $image;
        this.fishingAreaId = $fishingAreaId;
    }
}