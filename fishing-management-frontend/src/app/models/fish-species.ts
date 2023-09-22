export class FishSpecies {
    public id: number;
    public name: string;
    public latinName: string;
    public category: string;
    public minSize?: number;
    public maxQuantity?: number;
    public fishingBanStartDay: number;
    public fishingBanStartMonth: number;
    public fishingBanEndDay: number;
    public fishingBanEndMonth: number;
    public permanentFishingBan: boolean;
    public image: string;

    constructor($id: number, $name: string, $latinName: string, $category: string,
        $minSize: number, $maxQuantity: number, $fishingBanStartDay: number,
        $fishingBanStartMonth: number, $fishingBanEndDay: number,
        $fishingBanEndMonth: number, $permanentFishingBan: boolean, $image: string) {
        this.id = $id;
        this.name = $name;
        this.latinName = $latinName;
        this.category = $category;
        this.minSize = $minSize;
        this.maxQuantity = $maxQuantity;
        this.fishingBanStartDay = $fishingBanStartDay;
        this.fishingBanStartMonth = $fishingBanStartMonth;
        this.fishingBanEndDay = $fishingBanEndDay;
        this.fishingBanEndMonth = $fishingBanEndMonth;
        this.permanentFishingBan = $permanentFishingBan;
        this.image = $image;
    }
    

}