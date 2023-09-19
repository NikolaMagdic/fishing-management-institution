export class FishSpecies {
    public id: number;
    public name: string;
    public latinName: string;
    public category: string;
    public minSize?: number;
    public maxQuantity?: number;
    public fishingBanStart: Date;
    public fishingBanEnd: Date;
    public permanentFishingBan: boolean;
    public image: string;

    constructor($id: number, $name: string, $latinName: string, $category: string,
        $minSize: number, $maxQuantity: number, $fishingBanStart: Date,
        $fishingBanEnd: Date, $permanentFishingBan: boolean, $image: string) {
        this.id = $id;
        this.name = $name;
        this.latinName = $latinName;
        this.category = $category;
        this.minSize = $minSize;
        this.maxQuantity = $maxQuantity;
        this.fishingBanStart = $fishingBanStart;
        this.fishingBanEnd = $fishingBanEnd;
        this.permanentFishingBan = $permanentFishingBan;
        this.image = $image;
    }
    

}