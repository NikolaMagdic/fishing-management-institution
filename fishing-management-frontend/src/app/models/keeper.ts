export class Keeper {
    public id: number;
    public firstName: string;
    public lastName: string;
    public dateOfBirth: Date;
    public licenseNumber: string;

    constructor($id: number, $firstName: string, $lastName: string, $dateOfBirth: Date, $licenseNumber: string) {
        this.id = $id;
        this.firstName = $firstName;
        this.lastName = $lastName;
        this.dateOfBirth = $dateOfBirth;
        this.licenseNumber = $licenseNumber;
    }
}