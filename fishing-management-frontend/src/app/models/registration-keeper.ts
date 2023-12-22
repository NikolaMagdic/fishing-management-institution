export class RegistrationKeeper {
    public username: string;
    public password: string;
    public confirmPassword: string;
    public firstName: string;
    public lastName: string;
    public dateOfBirth: Date;
    public licenseNumber: string;

    constructor($username: string, $password: string, $confirmPassword: string, $firstName: string, 
        $lastName:string, $dateOfBirth: Date, $licenseNumber: string) {
        this.username = $username;
        this.password = $password;
        this.confirmPassword = $confirmPassword;
        this.firstName = $firstName;
        this.lastName = $lastName;
        this.dateOfBirth = $dateOfBirth;
        this.licenseNumber = $licenseNumber;
    }
}