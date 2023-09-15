export class AuthenticationResponse {
    public jwtToken: string;
    public expiresIn: number;
    public role: string;
    public correspondingTableId: number;

    constructor($jwtToken: string, $expiresIn: number, $role: string, $correspondingTableId: number) {
        this.jwtToken = $jwtToken;
        this.expiresIn = $expiresIn;
        this.role = $role;
        this.correspondingTableId = $correspondingTableId;
    }
}