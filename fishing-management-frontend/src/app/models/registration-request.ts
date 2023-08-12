export class RegistrationRequest {
    public username: string;
    public password: string;
    public firstName: string;
    public lastName: string;
    public dateOfBirth: Date;
    public address: string;
    public city: string;
    public category: string;

    constructor($username: string, $password: string, $firstName: string, $lastName:string,
        $dateOfBirth: Date, $address: string, $city: string, $category: string) {
        this.username = $username;
        this.password = $password;
        this.firstName = $firstName;
        this.lastName = $lastName;
        this.dateOfBirth = $dateOfBirth;
        this.address = $address;
        this.city = $city;
        this.category = $category;
    }
}