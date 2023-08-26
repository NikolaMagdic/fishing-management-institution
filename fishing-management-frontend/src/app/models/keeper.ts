export class Keeper {
    public id: number;
    public firstName: string;
    public lastName: string;
    public dateOfBirth: Date;

    constructor($id: number, $firstName: string, $lastName: string, $dateOfBirth: Date) {
        this.id = $id;
        this.firstName = $firstName;
        this.lastName = $lastName;
        this.dateOfBirth = $dateOfBirth;
    }
}