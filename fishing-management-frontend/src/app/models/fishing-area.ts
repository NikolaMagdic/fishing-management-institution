export class FishingArea {
    public id: number;
    public name: string;
    public description: string;
    public type: string;
    public image: string;

    constructor($id: number, $name: string, $description: string, $type: string, $image: string) {
        this.id = $id;
        this.name = $name;
        this.description = $description;
        this.type = $type;
        this.image = $image;
    }
}

