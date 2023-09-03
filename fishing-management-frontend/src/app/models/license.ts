export class License {
    public id: number;
    public type: string;
    public day: Date;
    public year: number;
    
    constructor($id: number, $type: string, $day: Date, $year: number) {
        this.id = $id;
        this.type = $type;
        this.day = $day;
        this.year = $year;
    }
}