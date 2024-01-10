export class FishingArea {
    public id: number;
    public name: string;
    public description: string;
    public type: string;
    public allowedFishing: boolean;
    public parentArea: number | null;
    public image: string;

    constructor($id: number, $name: string, $description: string, $type: string,
        $allowedFishing: boolean, $parentArea: number | null, $image: string) {
        this.id = $id;
        this.name = $name;
        this.description = $description;
        this.type = $type;
        this.allowedFishing = $allowedFishing;
        this.parentArea = $parentArea;
        this.image = $image;
    }
}

