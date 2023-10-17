export class License {
    public id: number;
    public type: string;
    public date: Date;
    public endDate: Date;
    public year: number;
    public spotId: number;
    
    constructor($id: number, $type: string, $date: Date, $endDate: Date, $year: number, $spotId: number) {
        this.id = $id;
        this.type = $type;
        this.date = $date;
        this.endDate = $endDate;
        this.year = $year;
        this.spotId = $spotId;
    }
}