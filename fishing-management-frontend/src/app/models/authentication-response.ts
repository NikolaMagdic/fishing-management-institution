export class AuthenticationResponse {
    public jwtToken: string;
    public expiresIn: number;
    public role: string;

    constructor($jwtToken: string, $expiresIn: number, $role: string) {
        this.jwtToken = $jwtToken;
        this.expiresIn = $expiresIn;
        this.role = $role;
    }
}