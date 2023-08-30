export class AuthenticationResponse {
    public jwtToken: string;
    public expiresIn: number;

    constructor($jwtToken: string, $expiresIn: number) {
        this.jwtToken = $jwtToken;
        this.expiresIn = $expiresIn;
    }
}