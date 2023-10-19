export class Fisherman {
    public id: number;
    public firstName: string;
    public lastName: string;
    public dateOfBirth: Date;
    public address: string;
    public city: string;
    public category: string;
    public license!: boolean;

    constructor($id: number, $firstName: string, $lastName: string, $dateOfBirth: Date,
        $address: string, $city: string, $category: string) {
            this.id = $id;
            this.firstName = $firstName;
            this.lastName = $lastName;
            this.dateOfBirth = $dateOfBirth;
            this.address = $address;
            this.city = $city;
            this.category = $category;
        }
}